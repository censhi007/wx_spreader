package test.travel.model;

import java.util.HashMap;
import java.util.Map;

import test.travel.inf.TI;

public class Train extends TI {
	
	private static final long serialVersionUID = 4722965761758473008L;
	private String No;
	//坐席
	private int s1;
	private int s2;
	private int s3;
	private int s4;
	private int s5;
	private int s6;
	private int s7;
	private int s8;
	private int s9;
	private int s10;
	
	private Map<String,Integer> sM=new HashMap<String,Integer>();
	//
	private String stime;
	private String etime;
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public int getS1() {
		return s1;
	}
	public void setS1(int s1) {
		this.s1 = s1;
	}
	public int getS2() {
		return s2;
	}
	public void setS2(int s2) {
		this.s2 = s2;
	}
	public int getS3() {
		return s3;
	}
	public void setS3(int s3) {
		this.s3 = s3;
	}
	public int getS4() {
		return s4;
	}
	public void setS4(int s4) {
		this.s4 = s4;
	}
	public int getS5() {
		return s5;
	}
	public void setS5(int s5) {
		this.s5 = s5;
	}
	public int getS6() {
		return s6;
	}
	public void setS6(int s6) {
		this.s6 = s6;
	}
	public int getS7() {
		return s7;
	}
	public void setS7(int s7) {
		this.s7 = s7;
	}
	public int getS8() {
		return s8;
	}
	public void setS8(int s8) {
		this.s8 = s8;
	}
	public int getS9() {
		return s9;
	}
	public void setS9(int s9) {
		this.s9 = s9;
	}
	public int getS10() {
		return s10;
	}
	public void setS10(int s10) {
		this.s10 = s10;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public Map<String, Integer> getsM() {
		return sM;
	}
	public void setsM(Map<String, Integer> sM) {
		this.sM = sM;
	}
	
	
}
