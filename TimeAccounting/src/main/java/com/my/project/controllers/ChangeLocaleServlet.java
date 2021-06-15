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
import com.my.project.dao.daoEntities.UsersDAO;
import com.my.project.model.Users;

@WebServlet("/changeLocale")
public class ChangeLocaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(ChangeLocaleServlet.class.getName());

	public ChangeLocaleServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Locale changing");
		UsersDAO userDAO = DAOFactory.getUserDAO("mysql");
		String locale = request.getParameter("locale");
		Users user = (Users) request.getSession().getAttribute("user");
		String currPage = null;
		try {
			user = userDAO.changeUserLocale(locale, user.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		if ((currPage = request.getParameter("currPage")) != null) {
			request.setAttribute("currPage", currPage);
		} else {
			request.setAttribute("currPage", request.getSession().getAttribute("currPage"));
		}
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("changeLocale.jsp").forward(request, response);
	}
}
