package com.wx.inf;

import org.dom4j.Document;

/**
 * Message wrapper interface
 * @author BUJUN
 *
 */
public interface MsInf {
	/**
	 * 从xml字符串中解析消息对象
	 * @param xml
	 * @return
	 */
	public Msg parse(Document xml);
}
