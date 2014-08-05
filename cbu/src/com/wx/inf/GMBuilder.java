package com.wx.inf;

import java.util.Map;
/**
 * 微信>>消息构造>>具体的消息构造器
 * @author BUJUN
 *
 */
public interface  GMBuilder extends MsInf,Cloneable{
	public  Msg parse(Map<String,String> map);
}
