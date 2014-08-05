package com.cbu.util;


/**
 * 发起调用者
 * @author BUJUN
 *
 */
public interface Caller {
	/**
	 * 将错误日志推回给调用者
	 * @param log
	 */
	public void pull(ErrLog log);
	
	public void setCall(Caller call);
}
