package com.wx.model.reciev;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wx.inf.Msg;

/**
 * 微信>> 接收消息>> 地理位置消息 
 * @author BUJUN
 *
 */
public class LcGMsg extends GMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190668507606996444L;
	//纬度
	private String Location_X;  
	//经度
	private String Location_Y;  
	//缩放
	private String Scale;  
	//地理信息
	private String Label;
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}  
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		Element root = doc.getRootElement();
		addElement("Location_X",getLocation_X(),root);	
		addElement("Location_Y",getLocation_Y(),root);
		addElement("Scale",getScale(),root);
		addElement("Label",getLabel(),root);	
		return doc;
	}
	public  Msg parse(Map<String,String> map){
		LcGMsg msg=(LcGMsg)super.parse(map);
		msg.setLocation_X(map.get("Location_X"));
		msg.setLocation_Y(map.get("Location_Y"));
		msg.setScale(map.get("Scale"));
		msg.setLabel(map.get("Label"));
		return msg;
	}
	public Msg parse(Document doc){
		LcGMsg msg=(LcGMsg)super.parse(doc);
		Element root = doc.getRootElement();
		Node n=root.selectSingleNode("Location_X");
		if(n!=null){
			msg.setLocation_X(n.getText());
		}
		n=root.selectSingleNode("Location_Y");
		if(n!=null){
			msg.setLocation_Y(n.getText());
		}
		n=root.selectSingleNode("Scale");
		if(n!=null){
			msg.setScale(n.getText());
		}
		n=root.selectSingleNode("Label");
		if(n!=null){
			msg.setLabel(n.getText());
		}
		return msg;
	}
}
