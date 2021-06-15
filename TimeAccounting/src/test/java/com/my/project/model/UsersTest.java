package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class UsersTest {

	@Test
	public void usersDefaultConstructorTest() {
		Users user = new Users();

		Assert.assertEquals(0, user.getId());
		Assert.assertEquals(null, user.getLogin());
		Assert.assertEquals(null, user.getPassword());
		Assert.assertEquals(null, user.getFirstname());
		Assert.assertEquals(null, user.getLastname());
		Assert.assertEquals(0, user.getRoleId());
		Assert.assertEquals(null, user.getLocale());
	}

	@Test
	public void usersConstructorTest() {
		Users user = new Users();
		user.setId(0);
		user.setLogin("t");
		user.setPassword("t");
		user.setFirstname("t");
		user.setLastname("t");
		user.setRoleId(1);
		user.setLocale("t");
		Users user1 = new Users("t", "t", "t", "t", 1, "t");
		Users user2 = new Users(0, "t", "t", "t", "t", 1, "t");
		Assert.assertEquals(user, user1);
		Assert.assertEquals(user, user2);
	}

	@Test
	public void userConstructor1Test() {
		Users user = new Users("t", "t");
		Users user1 = new Users();
		user1.setLogin("t");
		user1.setPassword("t");
		Assert.assertEquals(user, user1);
	}

	@Test
	public void usersToStringTest() {
		Users user1 = new Users();
		Users user2 = new Users(0, "t", "t", "t", "t", 1, "t");
		String expected1 = "Users [id=0, login=null, password=null, firstname=null, lastname=null, roleId=0, locale=null]";
		String expected2 = "Users [id=0, login=t, password=t, firstname=t, lastname=t, roleId=1, locale=t]";
		Assert.assertEquals(expected1, user1.toString());
		Assert.assertEquals(expected2, user2.toString());
	}

	@Test
	public void usersHashCodeTest() {
		Users user = new Users();
		int expected = 961;
		Assert.assertEquals(expected, user.hashCode());
	}

	@Test
	public void usersEqualsTest() {
		Activity activity1 = new Activity();
		Users user = null;
		Users user1 = new Users();
		Users user2 = new Users(1, "t", null, null, null, 0, null);
		Users user3 = new Users(1, null, "t", null, null, 0, null);

		Assert.assertEquals(true, user1.equals(user1));
		Assert.assertEquals(false, user1.equals(user));
		Assert.assertEquals(false, user1.equals(activity1));
		Assert.assertEquals(false, user1.equals(user2));
		Assert.assertEquals(false, user1.equals(user3));
	}
}
