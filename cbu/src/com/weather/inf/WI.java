package com.weather.inf;

import java.util.Collection;
import java.util.Date;

public abstract class WI implements java.io.Serializable,Cloneable{	
	private static final long serialVersionUID = 1L;

	public WI clone(){
		try {
			return (WI)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	/**
	 * 将对象转化为JSON字符串
	 * @return
	 */
	public abstract String toJSON();
	public abstract void fromJSON(String json);
	protected StringBuffer append(StringBuffer sb,String name,Object k){
		sb.append(name).append(":");
		append(sb,k);
		return sb;		
	}
	
	@SuppressWarnings("rawtypes")
	protected StringBuffer append(StringBuffer sb,Object k){
		if(k==null){
			sb.append("null");
			return sb;
		}
		if(k instanceof String){
			sb.append("'").append(k).append("'");
			return sb;
		}
		if(k instanceof Long || k instanceof Integer || 
		   k instanceof Short||k instanceof Boolean){
			sb.append("").append(k);
			return sb;
		}
		if(k instanceof Number){
			sb.append("").append(((Number)k).doubleValue());
			return sb;
		}
		if(k instanceof Date){
			sb.append("").append(((Date)k).getTime());
			return sb;
		}
		if(k instanceof WI){
			sb.append("").append(((WI)k).toJSON());
			return sb;
		}
		if(k instanceof Collection){
			sb.append("[");
			int i=0;
			for(Object o:(Collection)k){
				if(i==0){
					append(sb,o);					
				}else{
					sb.append(",");
					append(sb,o);	
				}
				i++;
			}
			sb.append("]");
		}
		return sb;
	}
}
