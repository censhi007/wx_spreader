package com.wx.inf;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Element;

/**
 * 微信>>XML通用功能
 * @author BUJUN
 *
 */
public abstract class MElement {

	@SuppressWarnings("rawtypes")
	protected Element addElement(String n,Object o,Element root){
		Element e=root.addElement(n);
		if(o==null || "".equals(o)){
			e.setText("#lt;![CDATA[]]#gt;");
			return e;
		}else if(o instanceof Date){
			e.setText(((Date)o).getTime()+"");
		}else if (o instanceof Collection || o instanceof Object[]){
			StringBuilder sb=new StringBuilder("#lt;![CDATA[");
			Iterator it=null;
			if(o instanceof Collection){
				it=((Collection)o).iterator();				
			}else{				
				it=Arrays.asList((Object[]) o).iterator();
			}
			while(it.hasNext()){
				Object i=it.next();
				sb.append(i).append(",");
			}
			int s=sb.length();
			if(s>0)sb.deleteCharAt(s-1);
			sb.append("]]#gt;");
			e.setText(sb.toString());
		}else if(o instanceof Number ){
			e.setText(o.toString());
		}else{
			e.setText("#lt;![CDATA["+o.toString()+"]]#gt;");
		}
		return e;
	}
}
