package com.my.project.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/increaseTime")
public class RedirectAddActivityTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(RedirectAddActivityTimeServlet.class.getName());

	public RedirectAddActivityTimeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("rederecting of increasing time");
		int status = Integer.parseInt(request.getParameter("s"));
		String message = request.getParameter("message");
		if (status == 1) {
			request.setAttribute("message", message);
		} else if (status == -1) {
			request.setAttribute("errmsg", message);
		} else {
			request.setAttribute("message", "Time wasn't added: " + message);
		}
		request.getRequestDispatcher("/useraccount").forward(request, response);
	}
}
