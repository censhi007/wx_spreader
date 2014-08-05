package com.cbu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.Util;


/**
 * 用于记录错误日志
 * @author BUJUN
 *
 */
public class ErrLog {
	private int state;
	private boolean bv;
	private String message;
	private int step;
	/**
	 * 上一条记录
	 */
	private ErrLog pre;
	/**
	 * 调用者
	 */
	private Object caller;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public boolean isBv() {
		return bv;
	}
	public void setBv(boolean bv) {
		this.bv = bv;
	}
	public String getMessage() {
		if(message!=null && message.contains("#")){
			Pattern p=Pattern.compile("#([^#]*)#");
			Matcher m=p.matcher(message);
			while(m.find()){
				String s=m.group(1);
				String ms=Util.getLocal(s);
				String ts=m.group();
				message=message.replace(ts, ms);
			}
		}
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public ErrLog getPre() {
		return pre;
	}
	public void setPre(ErrLog pre) {
		this.pre = pre;
	}
	public Object getCaller() {
		return caller;
	}
	public void setCaller(Object caller) {
		this.caller = caller;
	}
	
	public static ErrLog getLog(String message){
		ErrLog log=new ErrLog();
		log.setMessage(message);
		log.setStep(1);//第一步
		return log;
	}
	
	public  static void main(String []a){
		ErrLog.getLog("#log1.offline1#").getMessage();
	}
}
