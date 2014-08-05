package com.wx.model.reciev;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wx.inf.Msg;

/**
 * 微信>> 接收消息>> 图片消息
 * @author BUJUN
 *
 */
public class IMGMsg extends GMsg{
	
	private static final long serialVersionUID = -3039906744715783626L;
	/**
	 * 图片的连接
	 */
	private String PicUrl;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}  
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		addElement("PicUrl",getPicUrl(),doc.getRootElement());		
		return doc;
	}
	public  Msg parse(Map<String,String> map){
		IMGMsg msg=(IMGMsg)super.parse(map);
		msg.setPicUrl(map.get("PicUrl"));
		
		return msg;
	}
	public Msg parse(Document doc){
		IMGMsg msg=(IMGMsg)super.parse(doc);
		Element root = doc.getRootElement();
		Node n=root.selectSingleNode("PicUrl");
		if(n!=null){
			msg.setPicUrl(n.getText());
		}
		
		return msg;
	}
}
