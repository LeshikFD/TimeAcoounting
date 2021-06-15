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
import com.my.project.model.Activity;

@WebServlet("/addtime")
public class AddActivityTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(AddActivityTimeServlet.class.getName());

	public AddActivityTimeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Adding activity time");
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		int activityId = Integer.parseInt(request.getParameter("activityBtn"));
		int timeCount = 0;
		int status = 0;
		String message = "Time successfully added";
		try {
			if ((timeCount = Integer.parseInt(request.getParameter("addTime"))) < 0) {
				throw new NumberFormatException();
			}
			Activity activity = activityDAO.findActivityById(activityId);
			activityDAO.updateActivityByTime(activity, timeCount);
			status++;
		} catch (SQLException e) {
			logger.info(e.getMessage());
			message = e.getMessage();
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
			status = -1;
			message = "Field value should be positive number";
		}
		response.sendRedirect("increaseTime?s=" + status + "&message=" + message);
	}
}