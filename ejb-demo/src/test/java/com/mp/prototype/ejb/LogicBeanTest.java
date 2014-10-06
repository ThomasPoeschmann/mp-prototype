package com.mp.prototype.ejb;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LogicBeanTest {

	private LogicBean logicBean;

	@Before
	public void beforeTest() {
		logicBean = new LogicBean();
	}

	@Test
	public void testDateDifferent() throws Exception {
		long millis1 = logicBean.getTime();
		Thread.sleep(1000);
		long millis2 = logicBean.getTime();
		assertTrue(String.format("%s has to be less than %s", millis1, millis2), millis1 < millis2);
	}

}
