package com.mp.prototype.ejb;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class BusinessBean {

	public void doSleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// ignore
		}
	}

}
