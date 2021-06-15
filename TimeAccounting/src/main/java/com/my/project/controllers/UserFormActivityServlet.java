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

@WebServlet("/fillacitvity")
public class UserFormActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(UserFormActivityServlet.class.getName());

	public UserFormActivityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("User form for requesting activity");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		List<Category> categories = new ArrayList<>();
		try {
			categories = categoryDAO.getAllCategories();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		request.setAttribute("categories", categories);
		SecurityFilter.isUserFilter(request, response, "WEB-INF/view/userForm.jsp");
	}
}
