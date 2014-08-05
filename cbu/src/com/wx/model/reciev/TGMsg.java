package com.wx.model.reciev;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wx.inf.Msg;

/**
 * 微信>> 接收消息>> 文本消息
 * @author BUJUN
 *
 */
public class TGMsg extends GMsg{
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
	public  Msg parse(Map<String,String> map){
		TGMsg msg=(TGMsg)super.parse(map);
		String ct = map.get("Content");
		msg.setContent(ct);
		return msg;
	}
	public Msg parse(Document doc){
		TGMsg msg=(TGMsg)super.parse(doc);
		Element root = doc.getRootElement();
		Node c=root.selectSingleNode("Content");
		if(c!=null){
			msg.setContent(c.getText());
		}
		return msg;
	}
}
