package com.weather.pojo;

import com.weather.inf.WI;
/**
 * 用于计算每天或者每周、月、年等每个城市的访问次数
 * @author BUJUN
 *
 */
public class Coins extends WI{
	private String id;
	private Long count;//计数
	private Short stype;//某种计算方式。年，月，周，日
	private Long code;//某个城市
	private Integer ndbs;//某年
	private Integer month;//某月
	private Integer day;//从1900-01-01开始，到现在的天数.指定特定某天的访问计数
	private static final long serialVersionUID = 714105384525441832L;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Short getStype() {
		return stype;
	}

	public void setStype(Short stype) {
		this.stype = stype;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Integer getNdbs() {
		return ndbs;
	}

	public void setNdbs(Integer ndbs) {
		this.ndbs = ndbs;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Override
	public String toJSON() {
		StringBuffer sb=new StringBuffer("{");
		append(sb,"id",id);
		append(sb,"count",count);
		append(sb,"stype",stype);
		append(sb,"code",code);
		append(sb,"ndbs",ndbs);
		append(sb,"month",month);
		append(sb,"day",day);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public void fromJSON(String json) {
		
	}

}
