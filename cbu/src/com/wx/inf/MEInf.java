package com.wx.inf;

import org.dom4j.Element;
/**
 * 微信>>XML节点接口
 * @author BUJUN
 *
 */
public interface MEInf extends java.io.Serializable,Cloneable {

	public Element toXML();
}
