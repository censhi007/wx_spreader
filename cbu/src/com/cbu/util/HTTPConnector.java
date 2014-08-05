package com.cbu.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
/**
 * 用于http连接
 * @author BUJUN
 *
 */
@SuppressWarnings("deprecation")
public class HTTPConnector {
//	private Logger log=Logger.getLogger(HTTPConnector.class);
	private CloseableHttpClient client;
	private String id;
	private String c_string="";
	public HTTPConnector(){
		id=UUID.randomUUID().toString();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();  
	    cm.setMaxTotal(100);  
		client=HttpClients.custom().setConnectionManager(cm).build();
	}
	public HTTPConnector(String id){
		this.id=id;
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();  
	    cm.setMaxTotal(100);  
		client=HttpClients.custom().setConnectionManager(cm).build();
	}
	public HttpClient getClient() {
		return client;
	}
	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	private Map<String,String>cookies=new HashMap<String,String>();
	/**
	 * 获取当前客户端的cookie
	 * @param k
	 * @return
	 */
	public String getCookie(String k){
		return cookies.get(k);
	}
	/**
	 * 手动设置cookie
	 * @param key
	 * @param value
	 */
	public void setCookie(String key,String value){
		cookies.put(key,value);
		writeCookie();
	}
	/**
	 * 将cookie按客户端的不同，写入硬盘
	 */
	private void writeCookie(){
		StringBuffer sb=new StringBuffer();
		for(String k:cookies.keySet()){
			sb.append(k).append("=").append(cookies.get(k)).append(";");
		}
		this.c_string=sb.toString();		
		//TODO 将cookie写入硬盘
	}
	
	
	private String excute(HttpUriRequest request) throws ClientProtocolException, IOException{
		//以下的内容，需要进行同步控制。
				synchronized(client){
					CloseableHttpResponse response =client.execute(request);					
					//开始截取cookie
					Header[] hs=response.getHeaders("Set-Cookie");
					for(Header h:hs){
						String v=h.getValue();
						String[] vs=v.split(";");
						String[] vn=vs[0].split("=");
						if(vn.length<2)continue;
						cookies.put(vn[0], vn[1]);
					}
					writeCookie();//将cookies写到硬盘
					HttpEntity entity = response.getEntity();  
					BufferedReader reader =null;
					InputStreamReader ins=null;
					try{
						ins=new InputStreamReader(entity.getContent(), "UTF-8");
						reader=new BufferedReader(ins);  
						StringBuffer msg=new StringBuffer();
						String line=null;
						while((line=reader.readLine())!=null){
							msg.append(line);
						}
						
						msg.trimToSize();	
					//	log.info("返回内容为："+msg);
				    	return msg.toString();
						}finally{
							if(ins!=null)ins.close();
							if(reader!=null)reader.close();
							response.close();						
						}
				}
				
	
	}
	
	/**
	 * 通过get方法获取网络上的数据<br/>
	 * @param url 网络地址
	 * @param refer 发送时的引用头
	 * @param s302 遇到302是否自动跳转 
	 */
	public String get(String url,String refer,boolean s302) throws ClientProtocolException, IOException{
	//	log.info("--------------------start  request----------------------");
		URL turl = new URL(url);
    	HttpGet get = new HttpGet(url);  
    	get.addHeader("Host", turl.getHost());
		get.addHeader("Connection", "keep-alive");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		if(refer!=null && !"".equals(refer)){			
			get.addHeader("Referer", refer);
		}
		if(!"".equals(c_string)){			
			get.addHeader("Cookie", c_string);//总是装入cookie
			System.out.println(c_string);
		}
		get.addHeader("Keep-Alive", "300");
		get.addHeader("Connection", "keep-alive");
		if(s302){	
			RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).
					setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间			
			get.setConfig(requestConfig);

//			HttpParams params = client.getParams();    
//			params.setParameter(ClientPNames.HANDLE_REDIRECTS, false); 
		}
		return excute(get);
	}
	/**
	 * 通过get获取网络上的数据.遇到302时自动跳转
	 * @param url 网络地址
	 * @param refer http的引用头
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String get(String url,String refer) throws ClientProtocolException, IOException{
		return get(url,refer,false);
	}
	/**
	 * 通过get获取网络上的数据.遇到302时自动跳转
	 * @param url url 网络地址
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String get(String url) throws ClientProtocolException, IOException{
		return get(url,null,false);
	}
	/**
	 * 
	 */ 
	public byte[] getBin(String url,String refer) throws UnsupportedEncodingException, ClientProtocolException, IOException{
		
		URL turl = new URL(url);
    	HttpGet get = new HttpGet(url);  
    	get.addHeader("Host", turl.getHost());
		get.addHeader("Connection", "keep-alive");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Accept-Encoding","gzip,deflate,sdch");
		get.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		if(refer!=null && !"".equals(refer)){			
			get.addHeader("Referer", refer);
		}
		if(!"".equals(c_string)){			
			get.addHeader("Cookie", c_string);//总是装入cookie
			System.out.println(c_string);
		}
		get.addHeader("Keep-Alive", "300");
		get.addHeader("Connection", "keep-alive");
		//以下的内容，需要进行同步控制。
		synchronized(client){
			HttpResponse response =client.execute(get);			
			//开始截取cookie
			Header[] hs=response.getHeaders("Set-Cookie");
			for(Header h:hs){
				String v=h.getValue();
				String[] vs=v.split(";");
				String[] vn=vs[0].split("=");
				if(vn.length<2)continue;
				cookies.put(vn[0], vn[1]);
			}
			writeCookie();//将cookies写到硬盘
			Header aenc=response.getFirstHeader("Accept-Encoding");
			boolean gzip=false;
			if(aenc!=null){
				String ac=aenc.getValue();
				if(ac!=null && ac.length()>0){
					gzip=ac.indexOf("gzip")>-1;
				}
			}
			HttpEntity entity = response.getEntity();  			
			BufferedInputStream bis=null;;
			ByteOutputStream bos=null;
			BufferedOutputStream os=null;
			try{
				long lenth=entity.getContentLength();
				InputStream is=entity.getContent();
				if(gzip){
					is=new GZIPInputStream(is);
				}
				bis=new BufferedInputStream(entity.getContent());
				bos=new ByteOutputStream();
				os=new BufferedOutputStream(bos);
				byte[] bys=new byte[(int)lenth];
				while(bis.read(bys)!=-1){
					os.write(bys);
				}
				os.flush();
//				
				bis.read(bys);
			return bos.getBytes();
			}finally{
				if(bis!=null){
					bis.close();			
				}
				if(bos!=null){
					bos.close();
				}
				if(os!=null){
					os.close();
				}
			}
		}
	}
	
	public synchronized InputStream getStream(String url,String refer) throws ClientProtocolException, IOException{
		URL turl = new URL(url);
    	HttpGet get = new HttpGet(url);  
    	get.addHeader("Host", turl.getHost());
		get.addHeader("Connection", "keep-alive");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Accept-Encoding","gzip,deflate,sdch");
		get.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		if(refer!=null && !"".equals(refer)){			
			get.addHeader("Referer", refer);
		}
		if(!"".equals(c_string)){			
			get.addHeader("Cookie", c_string);//总是装入cookie
			System.out.println(c_string);
		}
		get.addHeader("Keep-Alive", "300");
		get.addHeader("Connection", "keep-alive");
		HttpResponse response =client.execute(get);			
		//开始截取cookie
		Header[] hs=response.getHeaders("Set-Cookie");
		for(Header h:hs){
			String v=h.getValue();
			String[] vs=v.split(";");
			String[] vn=vs[0].split("=");
			if(vn.length<2)continue;
			cookies.put(vn[0], vn[1]);
		}
		writeCookie();//将cookies写到硬盘
		Header aenc=response.getFirstHeader("Accept-Encoding");
		boolean gzip=false;
		if(aenc!=null){
			String ac=aenc.getValue();
			if(ac!=null && ac.length()>0){
				gzip=ac.indexOf("gzip")>-1;
			}
		}
		HttpEntity entity = response.getEntity();  	
		return entity.getContent();
	}
	/**
	 * 通过post方式获取网络上的内容
	 * @param paras post表单参数
	 * @param url 网络地址
	 * @param refer 请求的引用头
	 * 
	 */
	public String post(List<BasicNameValuePair> paras,String url,String refer) throws ClientProtocolException, IOException{
		//log.info("--------------------start  request----------------------");
		URL turl = new URL(url);
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uef = new UrlEncodedFormEntity(paras, "UTF-8");
		httppost.setEntity(uef);
		httppost.addHeader("Host", turl.getHost());
		httppost.addHeader("Connection", "keep-alive");
		httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17");
		httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httppost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httppost.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		if(refer!=null && !"".equals(refer)){			
			httppost.addHeader("Referer", refer);
		}
		if(!"".equals(c_string)){			
			httppost.addHeader("Cookie", c_string);//总是装入cookie
		}
		httppost.addHeader("Keep-Alive", "300");
		httppost.addHeader("Connection", "keep-alive");		
		return excute(httppost);
	}
	/**
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String post(String url) throws ClientProtocolException, IOException{
		return post(new ArrayList<BasicNameValuePair>(),url,null);
	}
	/**
	 * 
	 * @param paras
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String post(List<BasicNameValuePair> paras,String url) throws ClientProtocolException, IOException{
		return post(paras,url,null);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
