package com.wx.model.reciev;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wx.inf.Msg;

/**
 * 微信>> 接收消息>> 连接消息 
 * @author BUJUN
 *
 */
public class LkGMsg extends GMsg{
	
	private static final long serialVersionUID = -483871204520156723L;
	
	private String Title; 
	private String Description;  
	private String Url;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}  
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		Element root = doc.getRootElement();
		addElement("Title",getTitle(),root);	
		addElement("Description",getDescription(),root);
		addElement("Url",getUrl(),root);	
		return doc;
	}
	public  Msg parse(Map<String,String> map){
		LkGMsg msg=(LkGMsg)super.parse(map);
		msg.setTitle(map.get("Title"));
		msg.setDescription(map.get("Description"));
		msg.setUrl(map.get("Url"));
		return msg;
	}
	public Msg parse(Document doc){
		LkGMsg msg=(LkGMsg)super.parse(doc);
		Element root = doc.getRootElement();
		Node n=root.selectSingleNode("Title");
		if(n!=null){
			msg.setTitle(n.getText());
		}
		n=root.selectSingleNode("Description");
		if(n!=null){
			msg.setDescription(n.getText());
		}
		n=root.selectSingleNode("Url");
		if(n!=null){
			msg.setUrl(n.getText());
		}
		return msg;
	}
}
