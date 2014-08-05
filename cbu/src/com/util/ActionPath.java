package com.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.config.WebConfig;

/**
 * 用于添加自定义Action
 * @author BUJUN
 *
 */
public class ActionPath {
	private String classpath;
	private String methodName;
	private String type;
	public ActionPath(){
		
	}
	public ActionPath(String c,String m,String t){
		this.classpath=c;
		this.methodName=m;
		this.type=t;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public void excute(HttpServletRequest request,HttpServletResponse response){
		if(methodName==null || "".equals(methodName)){
			methodName="excute";
		}
		if("spring".equalsIgnoreCase(type)){
			Object o=WebConfig.getBean(classpath);
			if(o==null){
				throw new NullPointerException("spring中没有指定"+classpath);
			}
			Class<?> cls=o.getClass();
			try {
				Method m=cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class );
				m.invoke(o, request,response);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if(classpath==null || "".equals(classpath)){
			throw new NullPointerException("没有指定className");
		}
		try {
			Class<?> cls=Class.forName(classpath);
			Object o =cls.newInstance();
			Method m=cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class );
			m.invoke(o, request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
