package com.wx.model.send;

import org.dom4j.Document;

import com.wx.inf.Msg;

/**
 * 微信>> 发送消息>> 基类
 * @author BUJUN
 *
 */
@SuppressWarnings("serial")
public abstract class RMsg extends Msg{
	// 位0x0001被标志时，星标刚收到的消息 
	private int FuncFlag; 
	
	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}

	@Override
	public Document toXML() {
		Document doc = super.toXML();
		addElement("FuncFlag",FuncFlag,doc.getRootElement());		
		return doc;
	}
	
	public Msg CpFromMsg(Msg m){
		this.setCreateTime(System.currentTimeMillis());
		this.setFromUserName(m.getToUserName());
		this.setToUserName(m.getFromUserName());
		this.setFuncFlag(0);
		this.setMsgType("");
		return this;
	}
}
