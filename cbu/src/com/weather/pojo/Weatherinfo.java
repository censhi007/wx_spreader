package com.weather.pojo;

import java.util.HashMap;
import java.util.Map;

import com.weather.inf.Cache;
import com.weather.inf.WI;
import com.weather.util.Js;

public class Weatherinfo extends WI implements Cache<String, String>{
	private Map<String,String> wp= new HashMap<String,String>();
	private String func_string="(function(o){o=o||{weatherinfo:{}};o=o.weatherinfo||{};Weather=Weather||{};for(var i in o){if(o.hasOwnProperty(i) && typeof o[i]!='function'){Weather.put(i,o[i]);}}return Weather;})"; 
	
	private static final long serialVersionUID = 1288518263008241512L;
	public static final String TEMP1="temp1";
	public static final String WEATHER1="weather1";
	public static final String WIND1="wind1";
	public static final String IMG1="img1";
	public static final String IMG2="img2";
	public static final String INDEX_d="index_d";
	public static final String TEMP2="temp2";
	public static final String WEATHER2="weather2";
	public static final String WIND2="wind2";
	public static final String IMG3="img3";
	public static final String IMG4="img4";
	public static final String TEMP3="temp3";
	public static final String WEATHER3="weather3";
	public static final String WIND3="wind3";
	public static final String IMG5="img5";
	public static final String IMG6="img6";
	public static final String TODAY="date_y";
	@Override
	public String toJSON() {
		return null;
	}

	@Override
	public void fromJSON(String json) {
		Js js =new Js(new StringBuilder(func_string).append("(").append(json).append(");").toString());
		js.add("Weather", this);
		js.eval();
		js=null;
	}
	@Override
	public void put(String key, String value) {
		wp.put(key, value);
		
	}

	@Override
	public String get(String key) {
		return wp.get(key);
	}

	@Override
	public void remove(String key) {
		wp.remove(key);		
	}
	
	public String getCityName(){
		return wp.get("city");
	}
	public String getWeek(){
		return wp.get("week");
	}
	public String getTTemp(){
		return wp.get("temp1");
	}
	public String getTWth(){
		return wp.get("weather1");
	}
	public String getTImg1(){
		return wp.get("img1");
	}
	public String getTImg2(){
		return wp.get("img2");
	}
	public String getTWin(){
		return wp.get("wind1");
	}
	public String getDESCript(){
		return wp.get("index_d");
	}
	
	public String getToday(){
		return wp.get(TODAY);
	}
	public void clear(){
		wp.clear();
	}
}
