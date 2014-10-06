package com.mp.prototype.ejb;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

public class EmbeddedGlassfish {

	private static final Logger logger = Logger.getLogger(EmbeddedGlassfish.class.getName());

	private static final AtomicBoolean started = new AtomicBoolean(false);

	private static EJBContainer container;

	private static final String EAR_FILE_NAME = System.getProperty("mp-prototype.ejb-demo.earfilename");

	public static void startContainer() throws Exception {
		if (!started.compareAndSet(false, true)) {
			return;
		}

		System.setProperty("glassfish.embedded.tmpdir", "target/embedded-glassfish-instance");
		File file = new File(String.format("target/%s.jar", EAR_FILE_NAME));
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.MODULES, file);

		container = EJBContainer.createEJBContainer(properties);

	}

	@SuppressWarnings("unchecked")
	public static <T> T lookupByJNDIName(Class<T> clazz) throws NamingException {
		if (!started.get()) {
			throw new IllegalStateException("container isn't started");
		}
		final String jndiName = String.format("java:global/%s/%s", EAR_FILE_NAME, clazz.getSimpleName());
		logger.info("Looking up " + jndiName);
		return (T) container.getContext().lookup(jndiName);
	}

	public static void stopContainer() throws Exception {
		if (!started.compareAndSet(true, false)) {
			return;
		}

		container.close();
	}

}
