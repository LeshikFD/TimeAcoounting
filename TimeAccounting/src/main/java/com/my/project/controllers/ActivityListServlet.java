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

@WebServlet("/activityList")
public class ActivityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(ActivityListServlet.class.getName());

	public ActivityListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("List of acitivities");
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		try {
			request.getSession().setAttribute("activitiesList", activityDAO.getAllActivities());
		} catch (SQLException e) {
			logger.info("list wasn't taken" + e.getMessage());
		}
		request.getRequestDispatcher("activities?page=1").forward(request, response);
	}
}