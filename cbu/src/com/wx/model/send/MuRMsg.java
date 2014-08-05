package com.wx.model.send;

import org.dom4j.Document;

import com.wx.inf.Msg;
import com.wx.model.Music;
import com.wx.util.MsgBuilder;

/**
 * 微信>> 发送消息>> 音乐消息
 * @author BUJUN
 *
 */
public class MuRMsg extends RMsg{
	
	private static final long serialVersionUID = -9216555632780703967L;
	private Music music;
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		doc.getRootElement().add(getMusic().toXML());
		return doc;
	}
	public Msg CpFromMsg(Msg m){
		super.CpFromMsg(m);
		this.setMsgType(MsgBuilder.RESP_MESSAGE_TYPE_MUSIC);
		return this;
	}
}
