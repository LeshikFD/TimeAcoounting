package com.my.project.model;

import org.junit.Assert;
import org.junit.Test;

public class CategoryTest {
	@Test
	public void categoryDefaultConstructorTest() {
		Category category = new Category();

		Assert.assertEquals(0, category.getId());
		Assert.assertEquals(null, category.getName());
		Assert.assertEquals(null, category.getDescription());
	}

	@Test
	public void categoryConstructorTest() {
		Category category = new Category();
		category.setId(0);
		category.setName("t");
		category.setDescription("t");

		Category category1 = new Category("t", "t");
		Category category2 = new Category(0, "t", "t");
		Assert.assertEquals(category, category1);
		Assert.assertEquals(category, category2);
	}

	@Test
	public void categoryHashCodeTest() {
		Category category = new Category();
		Category category1 = new Category("t", "t");
		int expected = 961;
		int expected1 = 4673;
		Assert.assertEquals(expected, category.hashCode());
		Assert.assertEquals(expected1, category1.hashCode());
	}

	@Test
	public void categoryToStringTest() {
		Category category = new Category();
		Category category1 = new Category("t", "t");
		String expected = "Category [id=0, name=null, description=null]";
		String expected1 = "Category [id=0, name=t, description=t]";
		Assert.assertEquals(expected, category.toString());
		Assert.assertEquals(expected1, category1.toString());
	}

	@Test
	public void categoryEqualsTest() {
		Users user1 = new Users();
		Category category = null;
		Category category1 = new Category();
		Category category3 = new Category(0, "t", null);
		Category category4 = new Category(0, null, "t");

		Assert.assertEquals(true, category1.equals(category1));
		Assert.assertEquals(false, category1.equals(user1));
		Assert.assertEquals(false, category1.equals(category));
		Assert.assertEquals(false, category1.equals(category4));
		Assert.assertEquals(false, category1.equals(category3));

	}
}
