package com.my.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.model.Category;
import com.my.project.security.SecurityFilter;

@WebServlet("/categories")
public class AdminCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(AdminAgreementServlet.class.getName());

	public AdminCategoriesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Categories list");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		List<Category> categoryList = new ArrayList<>();
		try {
			categoryList = categoryDAO.getAllCategories();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		request.setAttribute("categoryList", categoryList);
		SecurityFilter.isAdminFilter(request, response, "WEB-INF/view/categories.jsp");
	}
}