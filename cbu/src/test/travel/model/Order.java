package test.travel.model;

import java.util.Date;
import java.util.List;

import test.travel.inf.TI;

public class Order extends TI {
	
	private static final long serialVersionUID = -8228385493888131925L;
	private User Loger;
	private List<Pssger> pss;
	private String from;
	private String to;
	private Date day;
	
	private List<String> tN;//车次
	private List<Short> sN;//席次
	private int pero;//优先
	public User getLoger() {
		return Loger;
	}
	public void setLoger(User loger) {
		Loger = loger;
	}
	public List<Pssger> getPss() {
		return pss;
	}
	public void setPss(List<Pssger> pss) {
		this.pss = pss;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public List<String> gettN() {
		return tN;
	}
	public void settN(List<String> tN) {
		this.tN = tN;
	}
	public List<Short> getsN() {
		return sN;
	}
	public void setsN(List<Short> sN) {
		this.sN = sN;
	}
	public int getPero() {
		return pero;
	}
	public void setPero(int pero) {
		this.pero = pero;
	}
	
	
}
