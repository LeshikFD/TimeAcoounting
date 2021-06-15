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
import com.my.project.dao.daoEntities.ActivityDAO;

@WebServlet("/filterActivity")
public class FilterActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(FilterActivityServlet.class.getName());

	public FilterActivityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Filtering activities");
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		try {
			request.getSession().setAttribute("activitiesList", activityDAO.getActivityListByCategory(categoryId));
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		request.getRequestDispatcher("activities?page=1").forward(request, response);
	}
}
