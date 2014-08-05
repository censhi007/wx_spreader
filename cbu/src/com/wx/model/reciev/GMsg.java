package com.wx.model.reciev;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wx.inf.GMBuilder;
import com.wx.inf.Msg;

/**
 * 微信>> 接收消息>>基类
 * @author BUJUN
 *
 */
@SuppressWarnings("serial")
public abstract class GMsg extends Msg implements GMBuilder{
	// 消息id，64位整型
	private long MsgId=0L;
	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		addElement("MsgId",MsgId,doc.getRootElement());		
		return doc;
	}
	public  Msg parse(Map<String,String> map){
		GMsg msg=this.clone();
		msg.setToUserName(map.get("ToUserName"));
		msg.setFromUserName(map.get("FromUserName"));
		msg.setMsgType(map.get("MsgType"));
		String om=map.get("CreateTime");
		if(om!=null){
			try{
				msg.setCreateTime(Long.parseLong(om));
			}catch(Exception e){
				
			}
		}
		om=map.get("MsgId");
		if(om!=null){
			try{
				msg.MsgId=Long.parseLong(om);
				om=null;
			}catch(Exception e){
				msg.MsgId=0;
			}
		}
		
		return msg;
	}
	public Msg parse(Document doc){
		GMsg msg=this.clone();
		Element root = doc.getRootElement();
		Node e = root.selectSingleNode("ToUserName");
		if(e!=null){
			msg.setToUserName(e.getText());
		}
		e=root.selectSingleNode("FromUserName");
		if(e!=null){
			msg.setFromUserName(e.getText());
		}
		e=root.selectSingleNode("MsgType");
		if(e!=null){
			msg.setMsgType(e.getText());
		}
		e=root.selectSingleNode("CreateTime");
		if(e!=null){
			String t=e.getText();
			if(t!=null){
				try{
					msg.setCreateTime(Long.parseLong(t));
				}catch(Exception ex){
					
				}
			}
		}
		e=root.selectSingleNode("MsgId");
		if(e!=null){
			String t=e.getText();
			if(t!=null){
				try{
					msg.setMsgId(Long.parseLong(t));
				}catch(Exception ex){
					
				}
			}
		}
		return msg;
	}
	
	public GMsg clone(){
		try {
			Object o = super.clone();
			return (GMsg)o;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
