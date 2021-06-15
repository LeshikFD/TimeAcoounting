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

@WebServlet("/orderActByName")
public class OrderActivityByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(OrderActivityByNameServlet.class.getName());

	public OrderActivityByNameServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Ordering by activity name");
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		try {
			String order = request.getParameter("sort");
			if ("asc".equals(order)) {
				request.getSession().setAttribute("activitiesList", activityDAO.getAllActivityByNameAsc());

			} else if ("desc".equals(order)) {
				request.getSession().setAttribute("activitiesList", activityDAO.getAllActivityByNameDesc());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		request.getRequestDispatcher("activities?page=1").forward(request, response);
	}
}
