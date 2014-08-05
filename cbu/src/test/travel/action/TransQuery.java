package test.travel.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import test.travel.inf.PTAction;
import test.travel.util.UR;

public class TransQuery extends PTAction{

	private static final long serialVersionUID = 1425215832198439356L;

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		
		String s=getString(request,"s","^[a-zA-Z]{1,10}$");
		String e=getString(request,"e","^[a-zA-Z]{1,10}$");
		String t=getString(request,"t","^\\d{4}-\\d{2}-\\d{2}$");
		s=s==null?"NONE":s;
		e=e==null?"NONE":e;
		t=t==null?"NONE":t;
		StringBuffer sb=new StringBuffer(UR.g_trains);
		sb.append("?leftTicketDTO.train_date=").append(t)
		.append("&leftTicketDTO.from_station=").append(s)
		.append("&leftTicketDTO.to_station=").append(e).append("&purpose_codes=ADULT");
		try {
			String script=con.get(sb.toString(),UR.rf_trains);
			Writer os=response.getWriter();
			System.out.println(script);
			os.write(script);
			os.flush();
			os.close();
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
