package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Util {
	private static String classpath;
	private static Properties prop;
	private static Properties prop_lang;
	public static String getClassPath(){
		if(classpath!=null)return classpath;
		String url = Util.class.getClassLoader().getResource("").getPath(); 
		try {
			url = java.net.URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		classpath=url.substring(0,url.lastIndexOf("/"));
		//访问https：12306时所需的证书。其他证书也可以通过本方法导入
		System.setProperty("javax.net.ssl.trustStore",classpath+"/12306cert");
		return classpath;
	}
	
	public static void setProp(Properties prop){
		Util.prop=prop;
	}
	
	public static String getProp(String key){
		return prop.getProperty(key);
	}
	public static Properties getPropLang(){
		InputStream is=null;
		if(prop_lang==null){
			try{
				String local=prop.getProperty("local");
				local=local==null||(!"1".equals(local) && !"EN".equalsIgnoreCase(local))?"zh":"en";
				String pp=getClassPath()+"config/Lang_";
				File f=new File(pp+local+".properties");
				if(!f.exists()){
					f=new File(pp+"zh.properties");
				}
				is=new FileInputStream(f);
				prop_lang=new Properties();
				prop_lang.load(is);
			}catch(Exception e){
				
			}finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return prop_lang;
	}
	public static String getLocal(String key){
		return getPropLang().getProperty(key);
	}
}
