package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class RolesTest {
	@Test
	public void rolesDefaultConstructorTest() {
		Roles roles = new Roles();
		Assert.assertEquals(0, roles.getId());
		Assert.assertEquals(null, roles.getName());
		roles.setId(1);
		roles.setName("t");
		Assert.assertEquals(1, roles.getId());
		Assert.assertEquals("t", roles.getName());
	}
}
