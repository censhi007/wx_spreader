package test.travel.util;

import java.util.HashMap;
import java.util.Map;

import com.util.ActionPath;

public class Util {
	public static Map<String,ActionPath> aM=new HashMap<String,ActionPath>();
	static{
		aM.put("vc.tto", new ActionPath("tr_vcode","excute","spring"));
		aM.put("ckvc.tto", new ActionPath("tr_vcode","ckvc","spring"));
		aM.put("login.tto", new ActionPath("tr_login","excute","spring"));
		aM.put("query.tto", new ActionPath("tr_query","excute","spring"));
		
	}

}
