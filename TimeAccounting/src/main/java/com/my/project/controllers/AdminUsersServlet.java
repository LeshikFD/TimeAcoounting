package com.my.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.my.project.security.SecurityFilter;

@WebServlet("/allusers")
public class AdminUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(AdminUsersServlet.class.getName());

	public AdminUsersServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("All users list");
		UsersDAO userDAO = DAOFactory.getUserDAO("mysql");
		List<Users> usersList = new ArrayList<>();
		try {
			usersList = userDAO.getAllUsers();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		request.setAttribute("usersList", usersList);
		SecurityFilter.isAdminFilter(request, response, "WEB-INF/view/users.jsp");

	}
}
