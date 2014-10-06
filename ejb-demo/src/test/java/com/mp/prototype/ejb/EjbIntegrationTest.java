package com.mp.prototype.ejb;

import org.junit.BeforeClass;
import org.junit.Test;

public class EjbIntegrationTest {

	@BeforeClass
	public static void beforeTest() throws Exception {
		EmbeddedGlassfish.startContainer();
	}

	@Test
	public void testGlassfish() throws Exception {
		SingletonDestinationBean bean = EmbeddedGlassfish.lookupByJNDIName(SingletonDestinationBean.class);
		bean.foo();
	}

}
