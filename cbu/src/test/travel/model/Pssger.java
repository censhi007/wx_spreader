package test.travel.model;

import test.travel.inf.TI;

public class Pssger extends TI{	
	private static final long serialVersionUID = -7295203582169448749L;
	
	private String id;
	private String name;
	private String mobile;
	private int nml=0;
	private String code;
	private String email;
	private int sex=0;
	
	private String from;
	private String to;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getNml() {
		return nml;
	}
	public void setNml(int nml) {
		this.nml = nml;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public Pssger getPss(String ID,String n,String mobile,String email,String code,String sex ){
		Pssger ps=new Pssger();
		ps.id=ID;
		ps.name=n;
		ps.mobile=mobile;
		ps.email=email;
		ps.code=code;
		ps.sex="M".equals(sex)?0:1;
		return ps;
	}
	
	
}
