package com.liquibase;



import java.sql.Connection;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import liquibase.ClassLoaderFileOpener;
import liquibase.CompositeFileOpener;
import liquibase.FileOpener;
import liquibase.FileSystemFileOpener;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.log.LogFactory;
import liquibase.servlet.LiquibaseStatusServlet;
import liquibase.util.NetUtil;

import org.apache.log4j.Logger;

/**
 * Servlet listener than can be added to web.xml to allow LiquiBase to run on every application server startup.
 * Using this listener allows users to know that they always have the most up to date database, although it will
 * slow down application server startup slightly.
 * See the <a href="http://www.liquibase.org/manual/latest/servlet_listener_migrator.html">LiquiBase documentation</a> for
 * more information.
 */
public class LiquibaseServletListener implements ServletContextListener {

	private Logger log = Logger.getLogger(this.getClass());
    private String changeLogFile;
    private String dataSource;
    private String contexts;


    public String getChangeLogFile() {
        return changeLogFile;
    }

    public void setContexts(String ctxt) {
        contexts = ctxt;
    }

    public String getContexts() {
        return contexts;
    }

    public void setChangeLogFile(String changeLogFile) {
        this.changeLogFile = changeLogFile;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	SystemInitServlet sis = new SystemInitServlet();
		ServletContext context = servletContextEvent.getServletContext();
    	try {
			sis.init(context);
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
    	LogFactory.getLogger().addHandler(new Handler() {        	
        	
            public synchronized void publish(LogRecord record) {
                LiquibaseStatusServlet.logMessage(record);
            }

            public void flush() {
            }

            public void close() throws SecurityException {
            }
        });
        String hostName;
        try {
            hostName = NetUtil.getLocalHost().getHostName();
        } catch (Exception e) {
        	context.log("Cannot find hostname: " + e.getMessage());
            return;
        }

        String shouldRunProperty = System.getProperty(Liquibase.SHOULD_RUN_SYSTEM_PROPERTY);
        if (shouldRunProperty != null && !Boolean.valueOf(shouldRunProperty)) {
            LogFactory.getLogger().info("LiquiBase did not run on " + hostName + " because '" + Liquibase.SHOULD_RUN_SYSTEM_PROPERTY + "' system property was set to false");
            return;
        }

        String machineIncludes = context.getInitParameter("LIQUIBASE_HOST_INCLUDES");
        String machineExcludes = context.getInitParameter("LIQUIBASE_HOST_EXCLUDES");
        String failOnError =context.getInitParameter("LIQUIBASE_FAIL_ON_ERROR");
        boolean shouldRun = false;
        if (machineIncludes == null && machineExcludes == null) {
            shouldRun = true;
        } else if (machineIncludes != null) {
            for (String machine : machineIncludes.split(",")) {
                machine = machine.trim();
                if (hostName.equalsIgnoreCase(machine)) {
                    shouldRun = true;
                }
            }
        } else if (machineExcludes != null) {
            shouldRun = true;
            for (String machine : machineExcludes.split(",")) {
                machine = machine.trim();
                if (hostName.equalsIgnoreCase(machine)) {
                    shouldRun = false;
                }
            }
        }
        if (!shouldRun) {
        	context.log("LiquibaseServletListener did not run due to LIQUIBASE_HOST_INCLUDES and/or LIQUIBASE_HOST_EXCLUDES");
            return;
        }

        setChangeLogFile(context.getInitParameter("LIQUIBASE_CHANGELOG"));
        setContexts(context.getInitParameter("LIQUIBASE_CONTEXTS"));
        if (getChangeLogFile() == null) {
            throw new RuntimeException("Cannot run LiquiBase, LIQUIBASE_CHANGELOG is not set");
        }

        try {
            Context ic = null;
            Connection connection = null;
            try {
                ic = new InitialContext();
                DataSource dataSource = sis.getDatasource();
                connection = dataSource.getConnection();
                FileOpener clFO = new ClassLoaderFileOpener();
                FileOpener fsFO = new FileSystemFileOpener();
                Liquibase liquibase = new Liquibase(getChangeLogFile(), new CompositeFileOpener(clFO,fsFO), DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection));

                liquibase.update(getContexts());
            } finally {
                if (ic != null) {
                    ic.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            if (!"false".equals(failOnError)) {
            	log.info("liquibase配置文件被修改而锁定...");
                throw new RuntimeException(e);
            }
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
