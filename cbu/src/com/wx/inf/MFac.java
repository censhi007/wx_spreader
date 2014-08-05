package com.wx.inf;

import javax.servlet.http.HttpServletRequest;

public interface MFac {
	public GMBuilder getBuilder(String name);
	public Msg parse(HttpServletRequest request);
}
