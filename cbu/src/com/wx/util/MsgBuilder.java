package com.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wx.inf.GMBuilder;
import com.wx.inf.MFac;
import com.wx.inf.MsInf;
import com.wx.inf.Msg;
import com.wx.model.reciev.IMGMsg;
import com.wx.model.reciev.LcGMsg;
import com.wx.model.reciev.LkGMsg;
import com.wx.model.reciev.TGMsg;
import com.wx.model.reciev.VGMsg;
import com.wx.model.send.ARRMsg;
import com.wx.model.send.MuRMsg;
import com.wx.model.send.RMsg;
import com.wx.model.send.TRMsg;
/**
 * 微信>>消息构造器>>通用工具<br/>
 * 需要扩展时，继承本工厂即可
 * @author BUJUN
 *
 */
public class MsgBuilder implements MsInf,MFac{
	private static final Map<String,GMBuilder> BUILDERS=new HashMap<String,GMBuilder>();
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	@SuppressWarnings("unchecked")
	@Override
	public Msg parse(Document xml) {
		Element root=xml.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		Map<String,String> map=new HashMap<String,String>();
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		xml=null;	
		GMBuilder gb=getBuilder(map.get(Msg.MSGTYPE));
		Msg msg=null;
		if(gb!=null){
			msg=gb.parse(map);			
		}
		map=null;
		return msg;
	}
	@Override
	public GMBuilder getBuilder(String name) {
		GMBuilder gb=BUILDERS.get(name);
		if(gb!=null)return gb;
		if(REQ_MESSAGE_TYPE_TEXT.equals(name)){
			//构造文本消息构造器
			gb=new TGMsg();
			BUILDERS.put(name, gb);
			return gb;
		}else if(REQ_MESSAGE_TYPE_IMAGE.equals(name)){
			//构造图片消息构造器
			gb=new IMGMsg();
			BUILDERS.put(name, gb);
			return gb;
		}else if(REQ_MESSAGE_TYPE_LINK.equals(name)){
			//连接消息构造器
			gb=new LkGMsg();
			BUILDERS.put(name, gb);
			return gb;
		}else if(REQ_MESSAGE_TYPE_LOCATION.equals(name)){
			//构造位置消息构造器
			gb=new LcGMsg();
			BUILDERS.put(name, gb);
			return gb;
		}else if(REQ_MESSAGE_TYPE_VOICE.equals(name)){
			//构造语音消息构造器
			gb=new VGMsg();
			BUILDERS.put(name, gb);
			return gb;
		}else if(REQ_MESSAGE_TYPE_EVENT.equals(name)){
			//构造事件消息构造器
			//gb=new VGMsg();
			//BUILDERS.put(name, gb);
			return gb;
		}
		return null;
	}
	@Override
	public Msg parse(HttpServletRequest request) {
		// 从request中取得输入流
		InputStream ins=null;
		try {
			ins = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document doc = reader.read(ins);
		//	LogFactory.getLog(MsgBuilder.class).info("接收到的数据:"+doc.asXML());
			return parse(doc);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(ins!=null){
				try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * 返回文本消息
	 * @param m
	 * @return
	 */
	public static Msg getTxtMsg(Msg m){
		RMsg tx=new TRMsg();
		tx.CpFromMsg(m);
		return tx;
	}
	/**
	 * 返回新闻消息
	 * @param m
	 * @return
	 */
	public static Msg getNewsMsg(Msg m){
		RMsg Nw=new ARRMsg();
		Nw.CpFromMsg(m);
		return Nw;
	}
	/**
	 * 返回音乐消息
	 * @param m
	 * @return
	 */
	public static Msg getMusicMsg(Msg m){
		RMsg mu=new MuRMsg();
		mu.CpFromMsg(m);
		return mu;
	}
}
