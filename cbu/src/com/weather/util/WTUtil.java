package com.weather.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import com.weather.imp.W2FTask;
import com.weather.inf.Cache;
import com.weather.pojo.City;
import com.weather.pojo.Weatherinfo;

/**
 * 获取天气预报的公用类
 * @author BUJUN
 *
 */
public class WTUtil {
	/**
	 * 储存天气信息的硬盘路径。默认为class路径
	 */
	private static String DISKLocation="classpath:com/weather/res/data";
	/**
	 * class path
	 */
	private static String cpth=null;
	/**
	 * 天气预报数据存储地址
	 */
	private static String dpth=null;
	
	public static void setDISKLocation(String location){
		DISKLocation=location;
		dpth=null;
		getDPth();
	}
	public static String getCPth(){
		if(cpth==null){
			URL x= WTUtil.class.getResource("WTUtil.class");
			String p = x.getPath();//.replace("com", newChar)
			x=null;
			try {
				p=java.net.URLDecoder.decode(p, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		//	p = p.replaceAll("/",File.separator);
			int s=p.length();
			p = p.substring(0,s-29);
			cpth = p;
		}
		return cpth;
	}
	public static String getDPth(){
		if(dpth==null){
			if(DISKLocation.startsWith("classpath:")||DISKLocation.startsWith("CLASSPATH:")){
				getCPth();
				File d = new File(cpth,DISKLocation.replace("classpath:","").replace("CLASSPATH:", ""));
				if(!d.exists()){
					d.mkdirs();
				}
				dpth=d.getAbsolutePath();
				d=null;
			}else{
				dpth=DISKLocation;
			}
		}
		return dpth;
	}
	/**
	 * 中央气象局URL
	 */
	//http://weather.com.cn/data/sk/101210101.html
	private static String ZYQXJURL="http://m.weather.com.cn/data/";
	private static long TDATE=0l;
	public static Weatherinfo getWeather(Long code){
		long n = getDate();
		Weatherinfo wth=null;
		String cName="WTH_"+code;
		if(n==TDATE){
			Object wther=get(cName);
			if(wther!=null){
				return (Weatherinfo)wther;				
			}
		}else{
			clear();
			TDATE=n;
		}
		String wh=getDPth()+"/"+n+"/"+code+"/wh.js";
		File js=new File(wh);
		if(js.exists()){
			FileReader fr=null;
			BufferedReader read=null;
			try{
				fr=new FileReader(js);
				read=new BufferedReader(fr);
				StringBuilder sb=new StringBuilder();
				String l = read.readLine();
				while(l!=null){
					sb.append(l).append("\r\n");
					l=read.readLine();
				}
				l=null;
				wth= new Weatherinfo();
				wth.fromJSON(sb.toString());
				put(cName,wth);
				return wth;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(fr!=null){
					try {
						fr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(read!=null){
					try {
						read.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			//从网络上获取
			String w=getW(ZYQXJURL+code+".html");
			wth= new Weatherinfo();
			wth.fromJSON(w);
			put(cName,wth);
			write2File(w,wh);
			return wth;
		}
		return null;
	}
	public static long getDate(){
		long l = 0l;
		Calendar c = Calendar.getInstance();
		l = c.get(Calendar.YEAR)*10000+(c.get(Calendar.MONTH)+1)*100+c.get(Calendar.DAY_OF_MONTH);
		return l;
	}

	@SuppressWarnings("rawtypes")
	private static Cache cache;
	/**
	 * 设置缓存。<br/>
	 * 如果使用缓存机制，需要实现Cache接口
	 * @param icache
	 */
	@SuppressWarnings("rawtypes")
	public static void setCache(Cache icache){
		cache=icache;
		if(cpth==null && cache!=null){
			Class c =cache.getClass();
			URL u = c.getClassLoader().getResource(".");
			String p = u.getPath();
			try {
				p=java.net.URLDecoder.decode(p, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cpth=p;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Cache getCahce(){
		return cache;
	}
	@SuppressWarnings({ "unchecked"})
	private static void put(String key,Object v){
		if(cache!=null){
			cache.put(key, v);
		}
	}
	@SuppressWarnings({ "unchecked" })
	private static Object get(String key){
		if(cache!=null){
			return cache.get(key);
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	private static void remove(String key){
		if(cache!=null){
			cache.remove(key);
		}
	}
	private static void clear(){
		if(cache!=null){
			cache.clear();
		}
	}
	public static String getW(String url) {
		URL serverUrl;
		HttpURLConnection conn;
		InputStream ins=null;
		StringBuffer res = new StringBuffer();
		try {
			serverUrl = new URL(url);
			conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod("GET");// "POST" ,"GET"
			//conn.addRequestProperty("Cookie", cookie);
			conn.addRequestProperty("Accept-Charset", "UTF-8;");// GB2312,
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn.connect();
			ins = conn.getInputStream();
			String charset = "UTF-8";
			InputStreamReader inr = new InputStreamReader(ins, charset);
			BufferedReader bfr = new BufferedReader(inr);

			String line = "";

			do {
				res.append(line);
				line = bfr.readLine();
			} while (line != null);
			conn=null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {

		}finally{
			if(ins!=null){
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res.toString();
	}
	
	static void write2File(String html,String pth){
		new Thread(new W2FTask(html,pth)).start();
	}
	/**
	 * 根据传过来的城市名。返回可识别的城市
	 * @param cityName
	 * @return
	 */
	public static List<City> find(String cityName){
		return PPCity.search(cityName);
	}
	/**
	 * 通过城市名获取天气预报<br/>
	 * 如果城市名无法识别成唯一的城市，那么返回null
	 * @param cityName
	 * @return
	 */
	public static Weatherinfo getWeather(String cityName){
		List<City> cl=PPCity.search(cityName);
		if(cl==null ||cl.size()>1)return null;
		return getWeather(cl.get(0).getCode());
	}
	
	public static void main(String  []args){
		Weatherinfo w = getWeather("浙江杭州");
		System.out.println("城市："+w.getCityName()+"\n今日："+w.getWeek()+"\n气温："+w.getTTemp()+"\n天气："+w.getTWth());
	}
}
