package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class AgreementTest {
	@Test
	public void agreementDefaultConstructorTest() {
		Agreement agreement = new Agreement();

		Assert.assertEquals(0, agreement.getUsersId());
		Assert.assertEquals(0, agreement.getActivityId());
		Assert.assertEquals(0, agreement.getStatusId());
	}

	@Test
	public void agreementConstructorTest() {
		Agreement agreement = new Agreement();
		agreement.setUsersId(1);
		agreement.setActivityId(1);
		agreement.setStatusId(1);

		Agreement agreement1 = new Agreement(1, 1);
		agreement1.setStatusId(1);
		Agreement agreement2 = new Agreement(1, 1, 1);
		Assert.assertEquals(agreement, agreement1);
		Assert.assertEquals(agreement, agreement2);
	}

	@Test
	public void agreementHashCodeTest() {
		Agreement agreement = new Agreement();
		Agreement agreement1 = new Agreement(1, 1);
		int expected = 29791;
		int expected1 = 30753;
		Assert.assertEquals(expected, agreement.hashCode());
		Assert.assertEquals(expected1, agreement1.hashCode());
	}
	
	@Test
	public void agreementToStringTest() {
		Agreement agreement = new Agreement();
		Agreement agreement1 = new Agreement(1, 1);
		String expected = "Agreement [usersId=0, activityId=0, statusId=0]";
		String expected1 = "Agreement [usersId=1, activityId=1, statusId=0]";
		Assert.assertEquals(expected, agreement.toString());
		Assert.assertEquals(expected1, agreement1.toString());
	}
	
	@Test
	public void agreementEqualsTest() {
		Users user1 = new Users();
		Agreement agreement = null;
		Agreement agreement0 = new Agreement();
		Agreement agreement1 = new Agreement(1, 0, 0);
		Agreement agreement2 = new Agreement(0, 1 , 0);
		Agreement agreement3 = new Agreement(0, 0, 1);

		Assert.assertEquals(true, agreement0.equals(agreement0));
		Assert.assertEquals(false, agreement0.equals(user1));
		Assert.assertEquals(false, agreement0.equals(agreement));
		Assert.assertEquals(false, agreement0.equals(agreement1));
		Assert.assertEquals(false, agreement0.equals(agreement2));
		Assert.assertEquals(false, agreement0.equals(agreement3));
	}
}
