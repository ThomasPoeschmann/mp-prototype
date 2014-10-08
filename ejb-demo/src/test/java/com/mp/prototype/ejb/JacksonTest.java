package com.mp.prototype.ejb;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	@Test
	public void testJackson() throws Exception {
		Date d = new Date();
		Demo demoSerial = new Demo();
		demoSerial.setBool(true);
		demoSerial.setDate(d);
		demoSerial.setInteger(42);
		demoSerial.setStr("test");
		String jackson = JsonHelper.serializeToJson(demoSerial);
		assertEquals(String.format("{\"str\":\"test\",\"date\":%s,\"bool\":true,\"integer\":42}", d.getTime()), jackson);
		Demo demoDeserial = JsonHelper.deserializeFromJson(jackson, Demo.class);
		assertEquals(true, demoDeserial.isBool());
		assertEquals(d, demoDeserial.getDate());
		assertEquals(42, demoDeserial.getInteger());
		assertEquals("test", demoDeserial.getStr());
	}

	private static class Demo {

		private String str;

		private Date date;

		private boolean bool;

		private int integer;

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public boolean isBool() {
			return bool;
		}

		public void setBool(boolean bool) {
			this.bool = bool;
		}

		public int getInteger() {
			return integer;
		}

		public void setInteger(int integer) {
			this.integer = integer;
		}

	}

	private static class JsonHelper {

		private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

		static {
			OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}

		private JsonHelper() {
		}

		public static <T> T deserializeFromJson(String json, Class<T> type) throws IOException {
			return OBJECT_MAPPER.readValue(json, type);
		}

		public static <T> String serializeToJson(T object) throws IOException {
			return OBJECT_MAPPER.writeValueAsString(object);
		}
	}

}
