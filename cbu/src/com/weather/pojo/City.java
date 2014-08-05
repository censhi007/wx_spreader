package com.weather.pojo;

import com.weather.inf.WI;
/**
 * 用户保存城市信息
 * @author BUJUN
 *
 */
public class City extends WI implements Comparable<City>{	
	private static final long serialVersionUID = 8699891990805777296L;
	private long code;
	private String cityName;
	private String chz;
	private String en;
	private String description;
	private long count=0l;
	
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getChz() {
		return chz;
	}

	public void setChz(String chz) {
		this.chz = chz;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toJSON() {
		StringBuffer sb=new StringBuffer("{");
		sb.append("code:").append(code).append(",");
		append(sb,"cityName",cityName);
		append(sb,"chz",chz);
		append(sb,"en",en);
		append(sb,"description",description);
		append(sb,"count",count);
		sb.append("}");
		return sb.toString();
	}
	public String toString(){
		return cityName+"["+code+"]";
	}

	@Override
	public int compareTo(City o) {
		if(code==o.code)return 0;
		return (code+"").compareTo(o.code+"");		
	}

	@Override
	public void fromJSON(String json) {
		// TODO Auto-generated method stub
		
	}
	
}
