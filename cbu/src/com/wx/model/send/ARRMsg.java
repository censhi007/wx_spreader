package com.wx.model.send;

import org.dom4j.Document;
import org.dom4j.Element;

import com.wx.inf.Msg;
import com.wx.model.Articles;
import com.wx.util.MsgBuilder;

/**
 * 微信>> 发送消息>> 图文消息
 * @author BUJUN
 *
 */
public class ARRMsg extends RMsg{
	
	private static final long serialVersionUID = 1574939542368450364L;
	private int ArticleCount;
	private Articles Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public Articles getArticles() {
		if(Articles==null)Articles=new Articles();
		return Articles;
	}
	public void setArticles(Articles articles) {
		Articles = articles;
	}
	
	@Override
	public Document toXML() {
		Document doc = super.toXML();
		Element root=doc.getRootElement();
		addElement("ArticleCount",getArticles().getArticleCount(),root);
		root.add(getArticles().toXML());
		return doc;
	}
	public Msg CpFromMsg(Msg m){
		super.CpFromMsg(m);
		this.setMsgType(MsgBuilder.RESP_MESSAGE_TYPE_NEWS);
		return this;
	}
}
