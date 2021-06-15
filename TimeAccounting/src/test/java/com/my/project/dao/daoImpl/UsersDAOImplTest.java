package com.my.project.dao.daoImpl;

import java.sql.SQLException;

import org.junit.Test;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.UsersDAO;

public class UsersDAOImplTest {
	private UsersDAO userDAO = DAOFactory.getUserDAO("mysql");
	
	@Test(expected = SQLException.class)
	public void UsersDAOImplFindUserByIdExceptionTest() throws SQLException {
		userDAO.findUserById(0);
	}
	
	@Test(expected = SQLException.class)
	public void UsersDAOImplFindLogInUserExceptionTest() throws SQLException {
		userDAO.findLogInUser("t", "t");
	}
}
