package com.my.project.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.my.project.model.Users;
import com.my.project.security.SecurityFilter;

@WebServlet("/adminaccount")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(AdminServlet.class.getName());

	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user = (Users)request.getSession().getAttribute("user");
		logger.info("Hi admin" + user.getFirstname());
		SecurityFilter.isAdminFilter(request, response, "WEB-INF/view/admin.jsp");
	}
}