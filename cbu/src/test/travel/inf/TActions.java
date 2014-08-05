package test.travel.inf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TActions extends java.io.Serializable{
	public void excute(HttpServletRequest request,HttpServletResponse response);
}
