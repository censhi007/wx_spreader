package com.wx.hlp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.wx.inf.Hlp;

public class Helper implements Hlp{
	private static final long serialVersionUID = -2704797318302730016L;
	private final static Pattern TipTXT=Pattern.compile("^[？\\?Hh]|(?:help)$");
	private final static String TIP="?|？|h|H";
	private final static String TXT="显示本帮助信息";
	private List<Hlp> hps=new ArrayList<Hlp>();
	@Override
	public boolean isHlper(String txt) {
		return TipTXT.matcher(txt).find();
	}

	@Override
	public String showHlp() {
		StringBuilder sb=new StringBuilder();
		sb.append(TIP).append(":\t\t ").append(TXT).append("\n");
		for(Hlp h:hps){
			sb.append(h.showHlp()).append("\n");
		}
		return sb.toString();
	}
	/**
	 * 多个符号用|隔开
	 */
	@Override
	public Hlp addHlp(String tx, String ctx) {
		String[] ss=tx.trim().split("/");
		SHelp h=new SHelp();
		h.setTxt(ctx);
		for(String s:ss){
			h.addTip(s);
		}
		hps.add(h);
		return h;
	}

}
