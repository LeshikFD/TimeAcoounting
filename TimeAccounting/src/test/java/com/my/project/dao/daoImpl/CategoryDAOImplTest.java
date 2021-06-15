package com.my.project.dao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.model.Category;

public class CategoryDAOImplTest {
	private CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");

	@Test
	public void ActivityDAOImplAddNewActivityExceptionTest() throws SQLException {
		List<Category> list =categoryDAO.getAllCategories();
		Assert.assertNotEquals(null, list);
	}
}
