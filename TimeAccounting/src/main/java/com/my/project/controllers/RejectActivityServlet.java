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

@WebServlet("/rejectact")
public class RejectActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(RejectActivityServlet.class.getName());

	public RejectActivityServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Reject activity");
		String app = request.getContextPath();
		int activityId = Integer.parseInt(request.getParameter("rejectact"));
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		int status = 0;
		String message = "Activity was deleted";
		try {
			activityDAO.deleteActivityById(activityId);
			status++;
		} catch (SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("app", app);
		request.setAttribute("user", request.getSession().getAttribute("user"));
		response.sendRedirect("rejectActivity?s=" + status + "&message=" + message);
	}
}