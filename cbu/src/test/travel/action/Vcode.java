package test.travel.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import test.travel.inf.TActions;
import test.travel.util.UR;

import com.cbu.util.HTTPConnector;

public class Vcode implements TActions{	
	private static final long serialVersionUID = 8519024988327636043L;
	private HTTPConnector con;
	public HTTPConnector getCon() {
		return con;
	}
	public void setCon(HTTPConnector con) {
		this.con = con;
	}
	
	public void excute(HttpServletRequest request,HttpServletResponse response){
		try {
			String id=request.getSession().getId();			
			con.setCookie("JSESSIONID", id);
			//byte[] f=con.getBin(UR.VCIMAG+"&sid="+Math.random(), null);
			InputStream is=con.getStream(UR.VCIMAG+"&sid="+Math.random(), null);
			OutputStream os=response.getOutputStream();
			response.setHeader("Content-Type", "image/jpeg");
			int l=0;
			byte[] bys=new byte[1024];
			int len=is.read(bys);
			while(len>0){
				l+=len;
				os.write(bys,0,len);
				len=is.read(bys);
			}
			is.close();
			os.flush();
			os.close();
			response.setHeader("Content-Length", l+"");
			System.out.println(l);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * 检查随机码是否正确
	 * @param request
	 * @param response
	 */
	public void ckvc(HttpServletRequest request,HttpServletResponse response){
		String vc=request.getParameter("vcode");
		try {
			String script=con.post(UR.ckVC+"?rand=sjrand&randCode="+vc);
			Writer os=response.getWriter();
			os.write(script);
			os.flush();
			os.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
