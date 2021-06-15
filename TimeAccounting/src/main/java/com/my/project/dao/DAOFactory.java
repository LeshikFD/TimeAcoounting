package com.my.project.dao;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.ActivityDAO;
import com.my.project.dao.daoEntities.AgreementDAO;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.dao.daoEntities.RoleDAO;
import com.my.project.dao.daoEntities.UsersActivityDAO;
import com.my.project.dao.daoEntities.UsersDAO;
import com.my.project.dao.daoImpl.ActivityDAOImpl;
import com.my.project.dao.daoImpl.AgreementDAOImpl;
import com.my.project.dao.daoImpl.CategoryDAOImpl;
import com.my.project.dao.daoImpl.RoleDAOImpl;
import com.my.project.dao.daoImpl.UsersActivityDAOImpl;
import com.my.project.dao.daoImpl.UsersDAOImpl;

public class DAOFactory {
	private DAOFactory() {
		
	}
	
	public static ActivityDAO getActivityDAO(String name) {
		if ("mysql".equals(name.toLowerCase())) {
			new DBConnectionPool("com.mysql.cj.jdbc.Driver");
			return new ActivityDAOImpl();
		}
		return null;
	}
	
	public static AgreementDAO getAreementDAO(String name) {
		if ("mysql".equals(name.toLowerCase())) {
			new DBConnectionPool("com.mysql.cj.jdbc.Driver");
			return new AgreementDAOImpl();
		}
		return null;
	}
	
	public static CategoryDAO getCategoryDAO(String name) {
		if ("mysql".equals(name.toLowerCase())) {
			new DBConnectionPool("com.mysql.cj.jdbc.Driver");
			return new CategoryDAOImpl();
		}
		return null;
	}
	
	public static RoleDAO getRoleDAO(String name) {
		if ("mysql".equals(name.toLowerCase())) {
			new DBConnectionPool("com.mysql.cj.jdbc.Driver");
			return new RoleDAOImpl();
		}
		return null;
	}
	
	public static UsersActivityDAO getUsersActivityDAO(String name) {
		if ("mysql".equals(name.toLowerCase())) {
			new DBConnectionPool("com.mysql.cj.jdbc.Driver");
			return new UsersActivityDAOImpl();
		}
		return null;
	}
	
	public static UsersDAO getUserDAO(String name) {
		if ("mysql".equals(name.toLowerCase())) {
			new DBConnectionPool("com.mysql.cj.jdbc.Driver");
			return new UsersDAOImpl();
		}
		return null;
	}

}
