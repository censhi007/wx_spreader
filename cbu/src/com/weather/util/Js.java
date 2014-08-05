package com.weather.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class Js {
	private String path;
	private String html;
	private BufferedInputStream bis;
	private Reader is;
	private ScriptEngineManager manager = new ScriptEngineManager();
    protected ScriptEngine engine = manager.getEngineByName("JavaScript");
    protected Bindings vars = new SimpleBindings();
    
    public Js(){
    	
    }
    public Js(String html){
    	this.html=html;
    }
    public Js(Reader r){
    	this.is=r;
    }
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Reader getIs() {
		if(is==null){
			if(html!=null){
					is=new BufferedReader(new StringReader(html));				
			}else if(path!=null){
				File f=new File(path);
				if(f.exists()){
					try {
						is=new FileReader(f);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return is;
	}
	public void setIs(Reader is) {
		this.is = is;
	}
	
	/**
	 * 关闭流
	 */
	public void destroy(){		
		if(is!=null)
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(bis!=null)
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public Object eval(){
		if(html!=null){
			try {
				return engine.eval(html,vars);
			} catch (ScriptException e) {
				e.printStackTrace();
				return null;
			}
			
		}else {
			try {
				return engine.eval(is, vars);
			} catch (ScriptException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public Object eval(String script) throws ScriptException{
		return engine.eval(script);
	}
	
	public Bindings getVars() {
		return vars;
	}
	public void setVars(Bindings vars) {
		this.vars = vars;
	}
	
	public void put(String name,Object value){
		this.vars.put(name, value);
	}
	public void add(String name,Object value){
		this.vars.put(name, value);
	}
}
