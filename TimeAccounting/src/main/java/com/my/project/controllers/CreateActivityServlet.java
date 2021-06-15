package com.my.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.ActivityDAO;
import com.my.project.dao.daoEntities.AgreementDAO;
import com.my.project.exceptions.EmptyFieldException;
import com.my.project.model.Activity;
import com.my.project.model.Users;

@WebServlet("/createActivity")
public class CreateActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(CreateActivityServlet.class.getName());

	public CreateActivityServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Creating new activity");
		request.setCharacterEncoding("UTF-8");
		ActivityDAO activityDAO = DAOFactory.getActivityDAO("mysql");
		AgreementDAO agreementDAO = DAOFactory.getAreementDAO("mysql");
		Activity activity = new Activity();
		String name = request.getParameter("actName");
		int activityId = 0;
		Users user = (Users) request.getSession().getAttribute("user");
		int status = 0;
		String message = "Request for new activity successfully sent";
		try {
			activityFieldsValidation(name);
			activity.setName(name);
			activity.setLastDate(getCurrentDateTime());
			activity.setTimeCount(0);
			activity.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			activityDAO.addNewActivity(activity);
			activityId = activityDAO.getActivityId(activity);
			agreementDAO.addNewAgreement(user.getId(), activityId, 1);
			status++;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			message = e.getMessage();
		} catch (EmptyFieldException e) {
			status = -1;
			message = e.getMessage();
		}
		response.sendRedirect("newActivity?s=" + status + "&message=" + message);
	}

	private void activityFieldsValidation(String name) throws EmptyFieldException {
		if ("".equals(name) || name == null || name.trim().length() == 0) {
			throw new EmptyFieldException("Activity name field should not be emty");
		}
	}

	private String getCurrentDateTime() {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		Date date = new Date(currentTime);
		return simpleDateFormat.format(date);
	}
}