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

import com.my.project.dao.daoEntities.AgreementDAO;
import com.my.project.model.Agreement;
import com.my.project.model.Users;

@WebServlet("/deleteRequest")
public class DeleteActivityRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(DeleteActivityRequestServlet.class.getName());

	public DeleteActivityRequestServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Deleting activity");
		AgreementDAO agreementDAO = DAOFactory.getAreementDAO("mysql");
		Users user = (Users) request.getSession().getAttribute("user");
		int activityId = 0;
		int status = 0;
		Agreement agreement = null;
		try {
			activityId = Integer.parseInt(request.getParameter("activityId"));
			agreement = agreementDAO.getAgreementByActivityId(activityId);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		if (agreement == null) {
			try {
				agreementDAO.addNewAgreement(user.getId(), activityId, 2);
				status = 1;
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		} else {
			status = -1;
		}
		response.sendRedirect("removeActivity?s=" + status + "&id=" + activityId);
	}
}