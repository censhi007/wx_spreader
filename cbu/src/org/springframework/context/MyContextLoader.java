package org.springframework.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.config.WebConfig;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 在完成spring加载后执行一些其他的操作
 * @author BUJUN
 *
 */
public class MyContextLoader extends ContextLoaderListener{
	public void contextInitialized(ServletContextEvent event) {
		  super.contextInitialized(event);
		  ServletContext context = event.getServletContext();
	        ApplicationContext ctx = WebApplicationContextUtils
	                .getRequiredWebApplicationContext(context);
	        
	        WebConfig.setCtx(ctx);
	        WebConfig.init();
	        System.out.println("完成单例context注入");
	}
}
