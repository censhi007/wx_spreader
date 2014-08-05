package org.springframework.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 必须先进行spring注入
 * @author BUJUN
 *
 */
public class WebConfig {
	public static ApplicationContext  ctx = null;

	public static void setCtx(ApplicationContext ctx) {
		WebConfig.ctx = ctx;
	} 
	public static ApplicationContext getCtx(){
		if(ctx==null){
			//当不是通过web应用调用本方法时，会先加载spring
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			init();
		}
		return ctx;
	}
	public static Object getBean(String key){
		return getCtx().getBean(key);
	}
	/**
	 * 一些其他的初始化操作。在spring完成注入后执行
	 */
	public static void init(){
		
	}
	

}
