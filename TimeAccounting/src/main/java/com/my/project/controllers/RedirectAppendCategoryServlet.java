package com.my.project.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/newCategory")
public class RedirectAppendCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(RedirectAppendCategoryServlet.class.getName());

	public RedirectAppendCategoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("rederecting new category");
		int status = Integer.parseInt(request.getParameter("s"));
		String message = request.getParameter("message");
		if (status == 1) {
			request.setAttribute("message", message);
		} else {
			request.setAttribute("errmsg", "Category wasn't added: " + message);
		}
		request.getRequestDispatcher("categories").forward(request, response);
	}

}
