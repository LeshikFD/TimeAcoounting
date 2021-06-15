package com.my.project.dao.daoImpl;

import java.sql.SQLException;

import org.junit.Test;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.ActivityDAO;
import com.my.project.model.Activity;

public class ActivityDAOImplTest {
	private ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
	
	@Test(expected = SQLException.class)
	public void ActivityDAOImplAddNewActivityExceptionTest() throws SQLException {
		Activity activity = new Activity();
		activityDAO.getActivityId(activity);
	}
	
	@Test
	public void ActivityDAOImplDeleteActivityByIdExceptionTest() throws SQLException {
		activityDAO.deleteActivityById(0);
	}
	
	@Test(expected = SQLException.class)
	public void ActivityDAOImplFindActivityByIdExceptionTest() throws SQLException {
		activityDAO.findActivityById(100);
	}
}
