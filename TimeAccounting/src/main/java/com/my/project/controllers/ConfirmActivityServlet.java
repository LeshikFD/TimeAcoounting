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
import com.my.project.dao.daoEntities.UsersActivityDAO;
import com.my.project.model.Agreement;

@WebServlet("/confirmact")
public class ConfirmActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(ConfirmActivityServlet.class.getName());

	public ConfirmActivityServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Activity was confirmed");
		int confirmActivityId = 0;
		int cancelActivityId = 0;
		String str;
		int status = 0;
		if ((str = request.getParameter("confirmact")) != null) {
			confirmActivityId = Integer.parseInt(str);
		}
		if ((str = request.getParameter("cancelact")) != null) {
			cancelActivityId = Integer.parseInt(str);
		}
		Agreement agreement = new Agreement();
		UsersActivityDAO userActivityDAO = DAOFactory.getUsersActivityDAO("mysql");
		AgreementDAO agreementDAO = DAOFactory.getAreementDAO("mysql");
		String message = "success";
		try {
			if (confirmActivityId != 0) {
				agreement = agreementDAO.getAgreementByActivityId(confirmActivityId);
				userActivityDAO.assignActivityForUser(agreement.getActivityId(), agreement.getUsersId());
				status = 1;
			} else if (cancelActivityId != 0) {
				agreement = agreementDAO.getAgreementByActivityId(cancelActivityId);
				status = 2;
			}
			agreementDAO.deleteAgreement(agreement);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			message = e.getMessage();
		}
		response.sendRedirect("confirmActivity?s=" + status + "&message=" + message);
	}
}
