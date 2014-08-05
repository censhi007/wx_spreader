package com.wx.hlp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.wx.inf.Hlp;

public class SHelp implements Hlp{

	private static final long serialVersionUID = 3254485249044133859L;
	private String txt="没有帮助信息";
	private List<String> tips=new ArrayList<String>();
	@Override
	public boolean isHlper(String txt) {
		return getTip().matcher(txt).find();
	}

	@Override
	public String showHlp() {
		return makeup()+":\t\t "+txt;
	}

	@Override
	public SHelp addHlp(String tx, String ctx) {
		return null;
	}
	
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	public void addTip(String t){
		getTips().add(t);
	}
	public List<String> getTips() {
		return tips;
	}

	public void setTips(List<String> tips) {
		this.tips = tips;
	}
	
	private String makeup(){
		StringBuilder sx=new StringBuilder();
		boolean f=false;
		for(String s:tips){
			if(f){
				sx.append("|");
			}
			f=true;
			sx.append(s);
		}
		return sx.toString();
	}
	
	private Pattern getTip(){
		StringBuilder sx=new StringBuilder("^");
		boolean f=false;
		for(String s:tips){
			if(f){
				sx.append("|");
			}
			f=true;
			sx.append("(?:").append(s).append(")");
		}
		return Pattern.compile(sx.append("$").toString());
	}
}
