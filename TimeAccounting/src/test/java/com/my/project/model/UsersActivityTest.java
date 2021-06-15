package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class UsersActivityTest {
	@Test
	public void usersActivityDefaultConstructorTest() {
		UsersActivity usersActivity = new UsersActivity();

		Assert.assertEquals(0, usersActivity.getUsersId());
		Assert.assertEquals(0, usersActivity.getActivityId());
	}

	@Test
	public void usersActivityConstructorTest() {
		UsersActivity usersActivity = new UsersActivity();
		usersActivity.setUsersId(1);
		usersActivity.setActivityId(1);

		UsersActivity usersActivity1 = new UsersActivity(1, 1);
		Assert.assertEquals(usersActivity, usersActivity1);
	}

	@Test
	public void usersActivityHashCodeTest() {
		UsersActivity usersActivity = new UsersActivity();
		UsersActivity usersActivity1 = new UsersActivity(1, 1);
		int expected = 961;
		int expected1 = 993;
		Assert.assertEquals(expected, usersActivity.hashCode());
		Assert.assertEquals(expected1, usersActivity1.hashCode());
	}

	@Test
	public void usersActivityToStringTest() {
		UsersActivity usersActivity = new UsersActivity();
		UsersActivity usersActivity1 = new UsersActivity(1, 1);
		String expected = "UsersActivity [usersId=0, activityId=0]";
		String expected1 = "UsersActivity [usersId=1, activityId=1]";
		Assert.assertEquals(expected, usersActivity.toString());
		Assert.assertEquals(expected1, usersActivity1.toString());
	}

	@Test
	public void usersActivityEqualsTest() {
		Users user1 = new Users();
		UsersActivity usersActivity = null;
		UsersActivity usersActivity1 = new UsersActivity();
		UsersActivity usersActivity2 = new UsersActivity(1, 0);
		UsersActivity usersActivity3 = new UsersActivity(0, 1);

		Assert.assertEquals(true, usersActivity1.equals(usersActivity1));
		Assert.assertEquals(false, usersActivity1.equals(user1));
		Assert.assertEquals(false, usersActivity1.equals(usersActivity));
		Assert.assertEquals(false, usersActivity1.equals(usersActivity2));
		Assert.assertEquals(false, usersActivity1.equals(usersActivity3));
	}
}
