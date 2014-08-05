package com.wx.inf;

public interface Hlp extends java.io.Serializable{
	/**
	 * 是否是本帮助信息
	 * @param txt
	 * @return
	 */
	public boolean isHlper(String txt);
	/**
	 * 显示本帮助信息
	 * @return
	 */
	public String showHlp();
	/**
	 * 添加帮助信息
	 * @param tx
	 * @param ctx
	 */
	public Hlp addHlp(String tx,String ctx);
}
