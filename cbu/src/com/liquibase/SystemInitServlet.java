package com.liquibase;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;

import com.sql.BasicDataSourceForKingyi;
import com.util.PropertiesFactory;
import com.util.Util;


@SuppressWarnings("serial")
public class SystemInitServlet extends HttpServlet  {
private Logger log = Logger.getLogger(this.getClass());
private BasicDataSource datasource;
public static final String DATABASE_TYPE_ORACLE = "oracle";
public static final String DATABASE_TYPE_MSSQL = "mssql";
public static final String DATABASE_TYPE_DB2 = "db2";
public static final String DATABASE_TYPE_MYSQL = "mysql";
public static final String DATABASE_TYPE_POSTGRES = "postgres";
public static final String DATABASE_TYPE_SYBASE = "sybase";
public static final String DATABASE_TYPE_INGRES = "ingres";
@SuppressWarnings("rawtypes")
public void init(ServletContext servletcontext) throws ServletException {
		System.out.println("开始生成配置文件...");
		File file = getJdbcProperties();
		Enumeration en = servletcontext.getInitParameterNames();
		for (Enumeration e = en; e.hasMoreElements();) {
			String thisName = e.nextElement().toString().trim();
			String thisValue = servletcontext.getInitParameter(thisName).trim();
			writeData(thisName, thisValue, file);
		}
		PropertiesFactory pf1 = new PropertiesFactory(file);
		createDiffUrlAndSoOn(pf1.getParam("database_type"),pf1.getParam("ip"),pf1.getParam("database"),pf1.getParam("server_name"),file);
		PropertiesFactory pf = new PropertiesFactory(file);
		Util.setProp(pf.getProperties());//灌注prop
		//更改日志路径
		changeLogByUser(pf.getParam("log_file"),"A2");
		//检查创建初始化数据库及liquibase解锁
		createInitDb(pf);
		
	}


/**
 * 更改日志路径
 */
private void changeLogByUser(String new_log_file_name,String apperder_name) {
	new_log_file_name = new_log_file_name.replace("\\", "/");
	new_log_file_name = new_log_file_name.replace("//", "/");
	File new_log_file = new File(new_log_file_name.substring(0, new_log_file_name.lastIndexOf("/")));
	if(!new_log_file.exists()){
		new_log_file.mkdirs();
	}
	@SuppressWarnings("static-access")
	DailyRollingFileAppender  drfa = (DailyRollingFileAppender) log.getRootLogger().getAppender(apperder_name);
	if(drfa==null)return;
	String log_file_name = drfa.getFile();
	try {
		drfa.setFile(new_log_file_name,true,drfa.getBufferedIO(),drfa.getBufferSize());
		drfa.activateOptions();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	if (log_file_name != null){
		File log_file = new File(log_file_name);
		if(log_file.exists()){
			log_file.delete();
		}
	}
}




/**
 * 获取各个数据库的url
 * @param databaseType
 * @param initParameter
 * @param initParameter2
 * @return
 */
private void createDiffUrlAndSoOn(String databaseType, String ip,
		String database,String server_name,File file) {
	writeData("url", getDiffUrl( databaseType,  ip, database,server_name), file);
	writeData("defaultUrl",getDiffUrl( databaseType,  ip, getDiffDefaultDatabase(databaseType),server_name),file);
	writeData("driverClassName", getDiffDriver( databaseType), file);
	writeData("sqldialect", getDiffSqldialect(databaseType), file);
}



private String getDiffDefaultDatabase(String databaseType) {
	String database = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		database = "";
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		database = "";
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		database = "pubs";
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		database = "test";
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		database = "postgres";
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		database = "master";
	}else if(databaseType.compareTo(DATABASE_TYPE_INGRES)==0){
		database = "rrp";
	}
	return database;
}


	/**
 * @param databaseType
 * @return
 */
private String getDiffSqldialect(String databaseType) {
	String sqldialect = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		sqldialect = "com.sql.sqldialect.OracleDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		sqldialect = "com.sql.sqldialect.Db2SQLDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		sqldialect = "com.sql.sqldialect.SQLServer2KDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		sqldialect = "com.sql.sqldialect.MysqlDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		sqldialect = "com.sql.sqldialect.PostgreDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		sqldialect = "com.sql.sqldialect.SybaseASEDialect";
	}
	return sqldialect;
}

	/**
 * @param databaseType
 * @return
 */
private String getDiffDriver(String databaseType) {
	String driverClassName = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		driverClassName = "oracle.jdbc.driver.OracleDriver";
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		driverClassName = "com.ibm.db2.jcc.DB2Driver";
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		driverClassName = "com.mysql.jdbc.Driver";
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		driverClassName = "org.postgresql.Driver";
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		driverClassName = "com.sybase.jdbc2.jdbc.SybDriver";
	}else if(databaseType.compareTo(DATABASE_TYPE_INGRES)==0){
		driverClassName = "com.ingres.jdbc.IngresDriver";
	}
	return driverClassName;
}



/**
 * @param databaseType
 * @param ip
 * @param database
 * @return
 */
private String getDiffUrl(String databaseType, String ip, String database,String server_name) {
	String url = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		url = "jdbc:oracle:thin:@"+ip+":"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		url = "jdbc:db2://"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		url = "jdbc:sqlserver://"+ip+";database="+database+";";
		if(server_name.compareToIgnoreCase("") != 0){
			url += "Data Source="+server_name+";";
		}
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		url = "jdbc:mysql://"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		url = "jdbc:postgresql://"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		url = "jdbc:sybase:Tds:"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_INGRES)==0){
		url = "jdbc:ingres://"+ip+"/"+database;
	}
	url=url+"?useUnicode=true&characterEncoding=UTF-8";
	return url;
}

	private void createInitDb(PropertiesFactory pf){		
		if(datasource==null)datasource=new BasicDataSourceForKingyi();
		else {
			try {
				datasource.close();//因为在spring中配置的存在连接
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String driverC=pf.getParam("driverClassName");
		if(driverC==null || driverC.isEmpty())
			driverC=datasource.getDriverClassName();
		String url=pf.getParam("url");
		if(url==null || url.isEmpty())
			url=datasource.getUrl();
		String un=pf.getParam("username");
		if(un==null || un.isEmpty())
			un=datasource.getUsername();
		String pwd=pf.getParam("password");
		if(pwd==null || pwd.isEmpty())
			pwd=datasource.getPassword();
		
		this.datasource.setDriverClassName(driverC);
		this.datasource.setUrl(url);
		this.datasource.setUsername(un);
		this.datasource.setPassword(pwd);
		Connection conn = null;
		try {
			conn = this.datasource.getConnection();
		} catch (SQLException e2) {
			log.info("自定义数据库连接失败...");
		}
		
		if (conn == null) {
			// 连接默认数据库
			BasicDataSource default_ds = new BasicDataSource();
			default_ds.setUrl(pf.getParam("defaultUrl"));
			default_ds.setPassword(pwd);
			default_ds.setUsername(un);
			default_ds.setDriverClassName(driverC);
			try {
				conn = default_ds.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (conn == null) {
				throw new RuntimeException("数据库默认库连接失败...请检查连接状态");
			} else {
				String sql = "CREATE DATABASE " + pf.getParam("database");
				log.info(sql);
				try {
					conn.createStatement().execute(sql);
					conn.close();
				} catch (SQLException e) {
					log.info("创建自定义数据库失败...");
					throw new RuntimeException(e);
				}
				try {
					conn = this.datasource.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String update_sql = "delete from databasechangeloglock";
		try {
			conn.createStatement().execute(update_sql);
			conn.close();
		} catch (SQLException e) {
		}

	}
	
	public File getJdbcProperties() {
		String url = Util.getClassPath(); 
		File file = new File(url, "jdbc.properties");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}
	
	public BasicDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(BasicDataSource datasource) {
		this.datasource = datasource;
	}
	
    public static void writeData(String key, String value,File file) {  
        Properties prop = new Properties();   
        try {   
            if (!file.exists())   
                file.createNewFile();   
            InputStream fis = new FileInputStream(file);   
            prop.load(fis);   
            fis.close();//一定要在修改值之前关闭fis   
            OutputStream fos = new FileOutputStream(file);   
            prop.setProperty(key, value);   
            prop.store(fos, "Update '" + key + "' value");   
            fos.close();   
        } catch (IOException e) {   
            System.err.println("Visit " + file + " for updating "  
                    + value + " value error");   
        }   
    }
}