package com.mp.prototype.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import javax.ejb.Singleton;

@Singleton
@Remote
public class SingletonDestinationBean {

	private int count = 0;

	@Asynchronous
	public void foo() {
		System.out.println("SingletonDestinationBean: entering foo");

		count++;

		try {
			Thread.sleep(200);
		} catch (InterruptedException ex) {
			// ignore
		}

		System.out.println("SingletonDestinationBean: leaving foo");
	}

	public int getCount() {
		return count;
	}

}
