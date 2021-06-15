package com.my.project.dao;

import org.junit.Assert;
import org.junit.Test;

import com.my.project.dao.daoEntities.ActivityDAO;
import com.my.project.dao.daoEntities.AgreementDAO;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.dao.daoEntities.RoleDAO;
import com.my.project.dao.daoEntities.UsersActivityDAO;
import com.my.project.dao.daoEntities.UsersDAO;

public class DAOFactoryTest {
	@Test
	public void daoFactoryGetActivityDAOTest() {
		ActivityDAO test = DAOFactory.getActivityDAO("mysql");
		Assert.assertNotEquals(null, test);
		ActivityDAO test1 = DAOFactory.getActivityDAO("sql");
		Assert.assertEquals(null, test1);
	}
	
	@Test
	public void daoFactoryGetAreementDAOTest() {
		AgreementDAO test = DAOFactory.getAreementDAO("mysql");
		Assert.assertNotEquals(null, test);
		AgreementDAO test1 = DAOFactory.getAreementDAO("sql");
		Assert.assertEquals(null, test1);
	}
	
	@Test
	public void daoFactoryGetCategoryDAOTest() {
		CategoryDAO test = DAOFactory.getCategoryDAO("mysql");
		Assert.assertNotEquals(null, test);
		CategoryDAO test1 = DAOFactory.getCategoryDAO("sql");
		Assert.assertEquals(null, test1);
	}
	
	@Test
	public void daoFactoryGetRoleDAOTest() {
		RoleDAO test = DAOFactory.getRoleDAO("mysql");
		Assert.assertNotEquals(null, test);
		RoleDAO test1 = DAOFactory.getRoleDAO("sql");
		Assert.assertEquals(null, test1);
	}
	
	@Test
	public void daoFactoryGetUsersActivityDAOTest() {
		UsersActivityDAO test = DAOFactory.getUsersActivityDAO("mysql");
		Assert.assertNotEquals(null, test);
		UsersActivityDAO test1 = DAOFactory.getUsersActivityDAO("sql");
		Assert.assertEquals(null, test1);
	}
	
	@Test
	public void daoFactoryGetUsersDAOTest() {
		UsersDAO test = DAOFactory.getUserDAO("mysql");
		Assert.assertNotEquals(null, test);
		UsersDAO test1 = DAOFactory.getUserDAO("sql");
		Assert.assertEquals(null, test1);
	}
}
