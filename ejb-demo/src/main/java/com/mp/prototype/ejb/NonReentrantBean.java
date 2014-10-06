package com.mp.prototype.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class NonReentrantBean {

	@EJB
	private NonReentrantBean selfProxy;

	@EJB
	private SingletonDestinationBean singletonDestinationBean;

	@EJB
	private BusinessBean businessBean;

	@Asynchronous
	public void asyncOuter() {
		System.out.println("NonReentrantBean: entering asyncOuter");

		selfProxy.asyncInner();
		businessBean.doSleep();

		System.out.println("NonReentrantBean: leaving asyncOuter");
	}

	@Asynchronous
	public void asyncInner() {
		System.out.println("NonReentrantBean: entering asyncInner");

		singletonDestinationBean.foo();
		businessBean.doSleep();

		System.out.println("NonReentrantBean: leaving asyncInner");
	}

}
