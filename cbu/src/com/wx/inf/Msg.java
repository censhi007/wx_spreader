package com.wx.inf;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 微信>>消息的基类
 * @author BUJUN
 *
 */
public abstract class Msg extends MElement implements java.io.Serializable{	
	private static final String TAG="xml"; 
	private static final long serialVersionUID = -4522337239285030624L;
	public static final String MSGTYPE="MsgType";
	// 开发者微信号
	private String ToUserName;
	// 发送方帐号（一个OpenID）
	private String FromUserName;
	// 消息创建时间 （长整型）
	private long CreateTime=0L;
	// 消息类型（text/image/location/link）
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}


	
	/**
	 *&lt;xml><br/>
	 *	&lt;ToUserName>&lt;![CDATA[toUser]]>&lt;/ToUserName><br/>
	 *	&lt;FromUserName>&lt;![CDATA[fromUser]]>&lt;/FromUserName><br/>
	 *	&lt;CreateTime>12345678&lt;/CreateTime><br/>
	 *	&lt;MsgType>&lt;![CDATA[text]]>&lt;/MsgType><br/>
	 *	&lt;Content>&lt;![CDATA[content]]>&lt;/Content><br/>
	 *	&lt;FuncFlag>0&lt;/FuncFlag><br/>
	 *&lt;/xml><br/>
	 * 将消息对像转化为标准XML字符串
	 * @return
	 */
	public Document toXML(){
		Document doc=DocumentHelper.createDocument();
		Element root=DocumentHelper.createElement(TAG);
		doc.add(root);
		addElement("ToUserName",ToUserName,root);
		addElement("FromUserName",FromUserName,root);
		addElement("CreateTime",CreateTime,root);
		addElement(MSGTYPE,MsgType,root);
		return doc;
	}
	
	public String asXML(){
		Document doc=toXML();
		String sxm=doc.asXML();
		sxm=sxm.replaceAll("#lt;", "<").replaceAll("#gt;", ">");
		return sxm;
	}
	
}
