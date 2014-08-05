package test.travel.inf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cbu.util.HTTPConnector;

public abstract class PTAction implements TActions{
	
	private static final long serialVersionUID = 3039158275722224459L;
	private Pattern p=null;
	private Matcher m;
	protected HTTPConnector con;
	public HTTPConnector getCon() {
		return con;
	}
	public void setCon(HTTPConnector con) {
		this.con = con;
	}
	public void excute(HttpServletRequest request, HttpServletResponse response){
		
	}
	public String getString(HttpServletRequest request,String key){
		return request.getParameter(key);
	}
	
	public String getString(HttpServletRequest request,String key,String pttern){
		String v=getString(request,key);
		p=Pattern.compile(pttern);
		m=p.matcher(v);
		if(m.find())return v;
		return null;
	}
}
