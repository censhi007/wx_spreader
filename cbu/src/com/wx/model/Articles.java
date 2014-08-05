package com.wx.model;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wx.inf.MEInf;
import com.wx.inf.MElement;
/**
 * 
 * @author Administrator
 *
 */
public class Articles extends MElement implements MEInf{
	private static final String TAG="Articles";
	private static final long serialVersionUID = 6513396649917082712L;
	private List<Article> arts;
	
	public List<Article> getArts() {
		return arts;
	}

	public void setArts(List<Article> arts) {
		this.arts = arts;
	}
	public int getArticleCount(){
		if(arts==null)return 0;
		return arts.size();
	}
	public void addArticle(Article a){
		if(arts==null){
			arts=new ArrayList<Article>();
		}
		arts.add(a);
	}
	/**
	 * 向Article集中加入图文
	 * @param title
	 * @param desc
	 * @param purl
	 * @param u
	 */
	public void addArticle(String title,String desc,String purl,String u){
		Article a = new Article();
		a.Description=desc;
		a.Title=title;
		a.PicUrl=purl;
		a.Url=u;
		addArticle(a);
	}
	
	@Override
	public Element toXML() {
		Element root=DocumentHelper.createElement(TAG);
		if(arts!=null){
			for(Article a:arts){
				root.add(a.toXML());
			}
		}
		return root;
	}
	public class Article extends MElement implements MEInf{

		private static final String TAG="item";
		private static final long serialVersionUID = -34904182582567683L;
		private String Title;  
		private String Description; 
		// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致  
		private String PicUrl;
		// 点击图文消息跳转链接 
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

		public String getPicUrl() {
			return PicUrl;
		}

		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}

		public String getUrl() {
			return Url;
		}

		public void setUrl(String url) {
			Url = url;
		}

		@Override
		public Element toXML() {
			Element root=DocumentHelper.createElement(TAG);
			addElement("Title",getTitle(),root);
			addElement("Description",getDescription(),root);
			addElement("PicUrl",getPicUrl(),root);
			addElement("Url",getUrl(),root);
			return root;
		}
		
	}
}
