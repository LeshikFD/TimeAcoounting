package com.my.project.dao.daoEntities;

import java.sql.SQLException;
import java.util.List;

import com.my.project.model.Category;

public interface CategoryDAO {
	List<Category> getAllCategories() throws SQLException;
	boolean addNewCategory(Category category) throws SQLException;
	boolean deleteCategoryById(int id) throws SQLException;
	
}
