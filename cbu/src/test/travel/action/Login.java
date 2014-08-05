package test.travel.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import test.travel.inf.Jsa;
import test.travel.inf.TActions;
import test.travel.model.Pssger;
import test.travel.model.User;
import test.travel.util.UR;

import com.cbu.util.HTTPConnector;
import com.util.Js;
import com.util.Util;

public class Login implements TActions{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6375135747287476663L;
	private HTTPConnector con;
	public HTTPConnector getCon() {
		return con;
	}
	public void setCon(HTTPConnector con) {
		this.con = con;
	}
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		String n=request.getParameter("uname");
		String pwd=request.getParameter("psd");
		String rand=request.getParameter("vcode");
		
		List<BasicNameValuePair> pL=new ArrayList<BasicNameValuePair>();
		pL.add(new BasicNameValuePair("loginUserDTO.user_name",n));
		pL.add(new BasicNameValuePair("userDTO.password",pwd));
		pL.add(new BasicNameValuePair("randCode",rand));
		
		try {
			String script=con.post(pL, UR.p_loginAsy);
			System.out.println(script);
			script="("+script+").data.loginCheck == 'Y';";
			Js js=new Js(script);
			Object o=js.eval();
			if(o!=null && o instanceof Boolean && (Boolean)o){				
				User u=new User();
				u.setUname(n);
				u.setPwd(pwd);
				u.setLstlog(new Date());
				request.getSession().setAttribute("Train_User", u);
				
				getPssgers(request,response);
				
				response.sendRedirect(request.getContextPath()+"/train/order.jsp");
				return;
			}
			request.setAttribute("script", script);
			request.setAttribute("ttyp", "login");
			request.getRequestDispatcher("tmp.jsp").forward(request, response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getPssgers(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Object ps=session.getAttribute("pssgers");
		if(ps!=null){
			return ;
		}
		try {
			String script =con.post(UR.p_pssgr);
			System.out.println(script);
			Js js=new Js();			
			js.setPath(Util.getClassPath()+"/test/travel/js/toPssger.js");
			js.getIs();
			js.put("psa", js.eval("(function(){ return "+script+";})();"));
			js.put("pssger", new Pssger());
			
			Object o=js.eval();
			if(o!=null){
				Jsa ja=(Jsa)o;
				session.setAttribute("pssgers", ja.getList());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
