
package com.sql;

import org.apache.commons.dbcp.BasicDataSource;

import com.cbu.util.DESPlus;

public class BasicDataSourceForKingyi extends BasicDataSource{
	@SuppressWarnings("unused")
	private volatile boolean restartNeeded = false;
	public void setPassword(String password) {
		try {
			password = new DESPlus().decrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			password = "";
			e.printStackTrace();
		}
        this.password = password;
        this.restartNeeded = true;
    }

}