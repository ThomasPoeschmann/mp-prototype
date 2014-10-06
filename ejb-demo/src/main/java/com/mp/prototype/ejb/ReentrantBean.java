package com.mp.prototype.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ReentrantBean {

	@EJB
	private ReentrantBean selfProxy;

	@EJB
	private SingletonDestinationBean singletonDestinationBean;

	@EJB
	private BusinessBean businessBean;

	@Lock(LockType.WRITE)
	public void doSomethingExclusive() {
		businessBean.doSleep();
	}

	@Asynchronous
	@Lock(LockType.READ)
	public void asyncOuter() {
		System.out.println("ReentrantBean: entering asyncOuter");

		selfProxy.doSomethingExclusive();

		selfProxy.asyncInner();
		businessBean.doSleep();

		System.out.println("ReentrantBean: leaving asyncOuter");
	}

	@Asynchronous
	@Lock(LockType.READ)
	public void asyncInner() {
		System.out.println("ReentrantBean: entering asyncInner");

		singletonDestinationBean.foo();
		businessBean.doSleep();

		System.out.println("ReentrantBean: leaving asyncInner");
	}

}
