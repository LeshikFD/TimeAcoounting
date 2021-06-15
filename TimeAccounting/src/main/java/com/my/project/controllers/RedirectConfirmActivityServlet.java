package com.my.project.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/confirmActivity")
public class RedirectConfirmActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(RedirectConfirmActivityServlet.class.getName());

	public RedirectConfirmActivityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("redirection of confirming activity");
		int status = Integer.parseInt(request.getParameter("s"));
		String message = request.getParameter("message");
		if (status == 1) {
			request.setAttribute("message", "Activity was confirmed");
		} else if (status == 2) {
			request.setAttribute("message", "Activity was cancelled");
		} else {
			request.setAttribute("message", "Activity wasn't confirmed: " + message);
		}
		request.getRequestDispatcher("/newrequest").forward(request, response);
	}
}
