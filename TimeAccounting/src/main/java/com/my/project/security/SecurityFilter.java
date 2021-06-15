package com.my.project.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.project.model.Users;

public class SecurityFilter {
	/**
	 * Method that receive user's request and response objects. Method looking for
	 * user in session with request object. When user was not found method redirects
	 * to authentication page. If user's role does not match the ADMIN method
	 * redirects to error page - AccessDeny, otherwise user successfully forwarding
	 * to requested page.
	 * 
	 * @param request  - user's request object
	 * @param response - user's response object
	 * @param link     - requested by user link
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void isAdminFilter(HttpServletRequest request, HttpServletResponse response, String link)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users currentUser = (Users) session.getAttribute("user");

		if (currentUser == null) {
			response.sendRedirect("logout");
		} else if (currentUser.getRoleId() != 2) {
			response.sendRedirect("AccessDenied.jsp");
		} else {
			request.getRequestDispatcher(link).forward(request, response);
		}
	}

	/**
	 * Method that receive user's request and response objects. Method looking for
	 * user in session with request object. When user was not found method redirects
	 * to authentication page. If user's role does not match the USER method
	 * redirects to error page - AccessDeny, otherwise user successfully forwarding
	 * to requested page.
	 * 
	 * @param request  - user's request object
	 * @param response - user's response object
	 * @param link     - requested by user link
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void isUserFilter(HttpServletRequest request, HttpServletResponse response, String link)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users currentUser = (Users) session.getAttribute("user");

		if (currentUser == null) {
			response.sendRedirect("logout");
		} else if (currentUser.getRoleId() != 1) {
			response.sendRedirect("AccessDenied.jsp");
		} else {
			request.getRequestDispatcher(link).forward(request, response);
		}
	}
}
