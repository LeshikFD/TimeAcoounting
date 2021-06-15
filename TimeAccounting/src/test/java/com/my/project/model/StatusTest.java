package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class StatusTest {
	@Test
	public void statusDefaultConstructorTest() {
		Status status = new Status();

		Assert.assertEquals(0, status.getId());
		Assert.assertEquals(null, status.getName());
	}

	@Test
	public void statusConstructorTest() {
		Status status = new Status();
		status.setId(1);
		status.setName("t");

		Status status1 = new Status(1, "t");
		Assert.assertEquals(status, status1);
	}

	@Test
	public void statusHashCodeTest() {
		Status status = new Status();
		Status status1 = new Status(1, "t");
		int expected = 961;
		int expected1 = 1108;
		Assert.assertEquals(expected, status.hashCode());
		Assert.assertEquals(expected1, status1.hashCode());
	}

	@Test
	public void statusToStringTest() {
		Status status = new Status();
		Status status1 = new Status(1, "t");
		String expected = "Status [id=0, name=null]";
		String expected1 = "Status [id=1, name=t]";
		Assert.assertEquals(expected, status.toString());
		Assert.assertEquals(expected1, status1.toString());
	}

	@Test
	public void statusEqualsTest() {
		Users user1 = new Users();
		Status status = null;
		Status status1 = new Status();
		Status status2 = new Status(0, "t");
		Status status3 = new Status(1, null);

		Assert.assertEquals(true, status1.equals(status1));
		Assert.assertEquals(false, status1.equals(user1));
		Assert.assertEquals(false, status1.equals(status));
		Assert.assertEquals(false, status1.equals(status2));
		Assert.assertEquals(false, status1.equals(status3));
	}
}
