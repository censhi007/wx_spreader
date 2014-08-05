package com.wx.model.reciev;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wx.inf.Msg;

/**
 * 微信>> 接收消息>> 语音消息
 * @author BUJUN
 *
 */
public class VGMsg extends GMsg{
	
	private static final long serialVersionUID = -2106248169734127998L;
	//媒体文件id
	private String MediaId;  
	//媒体文件格式
	private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}  
	
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		Element root = doc.getRootElement();
		addElement("MediaId",getMediaId(),root);
		addElement("Format",getFormat(),root);		
		
		return doc;
	}
	public  Msg parse(Map<String,String> map){
		VGMsg msg=(VGMsg)super.parse(map);
		msg.setMediaId(map.get("MediaId"));
		msg.setFormat(map.get("Format"));
		return msg;
	}
	public Msg parse(Document doc){
		VGMsg msg=(VGMsg)super.parse(doc);
		Element root = doc.getRootElement();
		Node n=root.selectSingleNode("MediaId");
		if(n!=null){
			msg.setMediaId(n.getText());
		}
		n=root.selectSingleNode("Format");
		if(n!=null){
			msg.setFormat(n.getText());
		}
		return msg;
	}
}
