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

@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(DeleteCategoryServlet.class.getName());

	public DeleteCategoryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Deleting category");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int status = 0;
		String message = "Category successfully deleted";
		try {
			categoryDAO.deleteCategoryById(categoryId);
			status++;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			message = e.getMessage();
		}
		response.sendRedirect("removeCategory?s=" + status + "&message=" + message);
	}
}