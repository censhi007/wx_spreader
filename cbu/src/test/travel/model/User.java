package test.travel.model;

import java.util.Date;
import java.util.List;

import test.travel.inf.TI;

public class User extends TI{
	
	private static final long serialVersionUID = 2226725258112229744L;

	private String uname;
	private String pwd;
	private boolean encypte;
	private Date lstlog;
	private Date fstlog;
	
	private List<?> traver;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isEncypte() {
		return encypte;
	}

	public void setEncypte(boolean encypte) {
		this.encypte = encypte;
	}

	public Date getLstlog() {
		return lstlog;
	}

	public void setLstlog(Date lstlog) {
		this.lstlog = lstlog;
	}

	public Date getFstlog() {
		return fstlog;
	}

	public void setFstlog(Date fstlog) {
		this.fstlog = fstlog;
	}

	public List<?> getTraver() {
		return traver;
	}

	public void setTraver(List<?> traver) {
		this.traver = traver;
	}
	
	
}
