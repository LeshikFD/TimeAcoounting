package com.my.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.AgreementDAO;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.model.Activity;
import com.my.project.model.Agreement;
import com.my.project.model.Category;
import com.my.project.model.Users;
import com.my.project.security.SecurityFilter;

@WebServlet("/newrequest")
public class AdminAgreementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(AdminAgreementServlet.class.getName());

	public AdminAgreementServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("List of agreements");
		AgreementDAO agreementDAO = DAOFactory.getAreementDAO("mysql");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		Map<Activity, Users> allActivityUser = new HashMap<Activity, Users>();
		List<Category> categories = new ArrayList<>();
		List<Agreement> allAgreements = new ArrayList<>();
		int reqForAddCount = 0;
		int reqForDelCount = 0;
		try {
			categories = categoryDAO.getAllCategories();
			reqForAddCount = agreementDAO.countAllAgreementsByStatus(1);
			reqForDelCount = agreementDAO.countAllAgreementsByStatus(2);
			allAgreements = agreementDAO.getAllAgreementsList();
			allActivityUser = agreementDAO.getAgreementsMap();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		request.setAttribute("categories", categories);
		request.setAttribute("allActivityUser", allActivityUser);
		request.setAttribute("allAgreements", allAgreements);
		request.setAttribute("reqForAddCount", reqForAddCount);
		request.setAttribute("reqForDelCount", reqForDelCount);
		SecurityFilter.isAdminFilter(request, response, "WEB-INF/view/admin.jsp");
	}
}