package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class ActivityTest {

	@Test
	public void activityDefaultConstructorTest() {
		Activity activity = new Activity();

		Assert.assertEquals(0, activity.getId());
		Assert.assertEquals(null, activity.getName());
		Assert.assertEquals(null, activity.getLastDate());
		Assert.assertEquals(0, activity.getTimeCount());
		Assert.assertEquals(0, activity.getCategoryId());
	}

	@Test
	public void activityConstructor1Test() {
		Activity activity = new Activity("test", "2021-06-09", 1, 1);

		Assert.assertEquals("test", activity.getName());
		Assert.assertEquals("2021-06-09", activity.getLastDate());
		Assert.assertEquals(1, activity.getTimeCount());
		Assert.assertEquals(1, activity.getCategoryId());
	}

	@Test
	public void activityConstructor2Test() {
		Activity activity = new Activity(1, "test", "2021-06-09", 1, 1);

		Assert.assertEquals(1, activity.getId());
		Assert.assertEquals("test", activity.getName());
		Assert.assertEquals("2021-06-09", activity.getLastDate());
		Assert.assertEquals(1, activity.getTimeCount());
		Assert.assertEquals(1, activity.getCategoryId());
	}

	@Test
	public void newActivityTest() {
		Activity activity = new Activity();
		activity.setId(1);
		activity.setName("test");
		activity.setLastDate("2021-06-09");
		activity.setTimeCount(1);
		activity.setCategoryId(1);

		Assert.assertEquals(1, activity.getId());
		Assert.assertEquals("test", activity.getName());
		Assert.assertEquals("2021-06-09", activity.getLastDate());
		Assert.assertEquals(1, activity.getTimeCount());
		Assert.assertEquals(1, activity.getCategoryId());
	}

	@Test
	public void activityToStringTest() {
		Activity activity = new Activity();
		Activity activity1 = new Activity(1, "test", "2021-06-09", 1, 1);
		String expected = "Activity [id=0, name=null, lastDate=null, timeCount=0, categoryId=0]";
		String expected1 = "Activity [id=1, name=test, lastDate=2021-06-09, timeCount=1, categoryId=1]";
		Assert.assertEquals(expected, activity.toString());
		Assert.assertEquals(expected1, activity1.toString());
	}

	@Test
	public void activityHashCodeTest() {
		Activity activity = new Activity();
		int expected = 29791;
		Assert.assertEquals(expected, activity.hashCode());
	}
	
	@Test
	public void activityEqualsTest() {
		Activity activity1 = new Activity();
		Activity activity2 = null;
		UsersTest user = new UsersTest();
		Activity activity3 = new Activity(1, "t", null, 0, 0);
		Activity activity4 = new Activity(1, null, "t", 0, 0);
		Activity activity5 = new Activity(1, null, null, 1, 0);
		Activity activity6 = new Activity(1, null, null, 0, 1);
		
		Assert.assertEquals(true, activity1.equals(activity1));
		Assert.assertEquals(false, activity1.equals(activity2));
		Assert.assertEquals(false, activity1.equals(user));
		Assert.assertEquals(false, activity1.equals(activity3));
		Assert.assertEquals(false, activity1.equals(activity4));
		Assert.assertEquals(false, activity1.equals(activity5));
		Assert.assertEquals(false, activity1.equals(activity6));
	}

}
