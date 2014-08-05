package com.wx.model.send;

import org.dom4j.Document;

import com.wx.inf.Msg;
import com.wx.util.MsgBuilder;

/**
 * 微信>> 发送消息>> 文本消息
 * @author BUJUN
 *
 */
public class TRMsg extends RMsg{
	private static final long serialVersionUID = 3800725608427416760L;
	/**
	 * 文本消息消息体
	 */
	private String Content;  
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public Document toXML() {
		Document doc = super.toXML();
		addElement("Content",getContent(),doc.getRootElement());		
		return doc;
	}
	public Msg CpFromMsg(Msg m){
		super.CpFromMsg(m);
		this.setMsgType(MsgBuilder.RESP_MESSAGE_TYPE_TEXT);
		return this;
	}
}
