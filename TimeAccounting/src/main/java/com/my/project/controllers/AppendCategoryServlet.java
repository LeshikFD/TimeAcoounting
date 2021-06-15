package com.my.project.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.exceptions.EmptyFieldException;
import com.my.project.model.Category;

@WebServlet("/appendCategory")
public class AppendCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(AppendCategoryServlet.class.getName());

	public AppendCategoryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("new category");
		request.setCharacterEncoding("UTF-8");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		Category category = new Category();
		String name = request.getParameter("categoryName");
		String description = request.getParameter("categoryDescr");
		int status = 0;
		String message = "New category successfully added";
		try {
			categoryFieldsValidation(name, description);
			category.setName(name);
			category.setDescription(description);
			categoryDAO.addNewCategory(category);
			status++;
		} catch (EmptyFieldException | SQLException e) {
			message = e.getMessage();
			logger.error(e.getMessage());
		}
		response.sendRedirect("newCategory?s=" + status + "&message=" + message);
	}

	private void categoryFieldsValidation(String name, String descr) throws EmptyFieldException {
		if ("".equals(name) || name == null || name.trim().length() == 0) {
			throw new EmptyFieldException("Category name field should not be emty");
		} else if ("".equals(descr) || descr == null || descr.trim().length() == 0) {
			throw new EmptyFieldException("Category description field should not be emty");
		}
	}
}
