package com.mp.prototype.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class Scheduler {

	@EJB
	NonReentrantBean nonReentrantBean;

	@EJB
	ReentrantBean reentrantBean;

	@Schedule(second = "*/30", hour = "*", minute = "*", persistent = false)
	public void onTimeout() {
		System.out.println("Scheduler: entering onTimeout");

		reentrantBean.asyncOuter();
		nonReentrantBean.asyncOuter();

		System.out.println("Scheduler: leaving onTimeout");
	}
}
