package com.mp.prototype.ejb;

import javax.ejb.Stateless;

@Stateless
public class LogicBean {

	public long getTime() {
		return System.currentTimeMillis();
	}

}
