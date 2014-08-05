package test.travel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.travel.model.User;

import com.util.ActionPath;

public class TrainFilter implements Filter {

	private String home="/train/login.jsp";
	//private String index="";
	@Override
	public void destroy() {
		
	}
  
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session=request.getSession();
		request.setCharacterEncoding("utf-8");
		String cpt = request.getContextPath();
		String sp = request.getServletPath();
		User lu=(User)session.getAttribute("Train_User");
		if(lu==null){
			if(!home.equals(sp)&&!sp.endsWith("vc.tto")&&!sp.endsWith("login.tto")&&!sp.endsWith("ckvc.tto")&&!sp.endsWith(".js")){
				try{
				response.sendRedirect(cpt+home);
				return;
				}catch(Exception e){}
			}else{
				if(sp.endsWith(".tto")){//train ticket order
					response.setCharacterEncoding("utf-8");
				//	response.setContentType("html");
					ActionPath ap=test.travel.util.Util.aM.get(getAP(sp));
					if(ap!=null){
						ap.excute(request, response);
					}
					return;
				}
				chain.doFilter(request, response);
			}
			return;
		}
		if(sp.endsWith(".tto")){//train ticket order
			response.setCharacterEncoding("utf-8");
		//	response.setContentType("html");
			ActionPath ap=test.travel.util.Util.aM.get(getAP(sp));
			if(ap!=null){
				ap.excute(request, response);
			}
			return;
		}
		chain.doFilter(request, response);
	}
	
	private String getAP(String p){
		if(p.contains("/")){
			return p.substring(p.lastIndexOf("/")+1);
		}
		return p;
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
