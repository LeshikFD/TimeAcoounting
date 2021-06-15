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

@WebServlet("/removeActivity")
public class RedirectDeleteActivityRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(RedirectDeleteActivityRequestServlet.class.getName());

	public RedirectDeleteActivityRequestServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("redirection of removing activity");
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		int status = Integer.parseInt(request.getParameter("s"));
		int activityId = Integer.parseInt(request.getParameter("id"));
		Activity activity = new Activity();
		try {
			activity = activityDAO.findActivityById(activityId);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		if (status == 1) {
			request.setAttribute("message", "Request for deletion activity ("+activity.getName()+ ") successfully sent");
		}else if(status == -1){
			request.setAttribute("message", "You have already sent a request to delete activity - " +activity.getName());
		}else {
			request.setAttribute("message", "Request wasn't sent");
		}
		request.getRequestDispatcher("/useraccount").forward(request, response);
	}
}
