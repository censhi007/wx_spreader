package com.wx.model;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wx.inf.MEInf;
import com.wx.inf.MElement;
/**
 * 微信>>公用>>音乐节点
 * @author BUJUN
 *
 */
public class Music extends MElement implements MEInf{
	private static final String TAG="Music";
	private static final long serialVersionUID = 3674697110583934035L;
	
	private String Title;  
	private String Description;  
	// 音乐链接  
	private String MusicUrl; 
	//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HQMusicUrl; 
	
	
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


	public String getMusicUrl() {
		return MusicUrl;
	}


	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}


	public String getHQMusicUrl() {
		return HQMusicUrl;
	}


	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}


	public static String getTag() {
		return TAG;
	}


	@Override
	public Element toXML() {
		Element root=DocumentHelper.createElement(TAG);
		addElement("Title",getTitle(),root);
		addElement("Description",getDescription(),root);
		addElement("MusicUrl",getMusicUrl(),root);
		addElement("HQMusicUrl",getHQMusicUrl(),root);
		return root;
	}

}
