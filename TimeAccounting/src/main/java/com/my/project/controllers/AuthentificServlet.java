package com.my.project.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.my.project.dao.DAOFactory;
import com.my.project.dao.daoEntities.UsersDAO;
import com.my.project.exceptions.EmptyFieldException;
import com.my.project.model.Users;

@WebServlet("/authentific")
public class AuthentificServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LogManager.getLogger(AuthentificServlet.class.getName());
	
    public AuthentificServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("authentic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Authentification");
		String login =  "";
		String pass = "";
		Users user = new Users();
		String app = request.getContextPath();
		HttpSession s = request.getSession();
		UsersDAO userDAO = DAOFactory.getUserDAO("mysql");
		try {
			login = request.getParameter("login");
			pass = request.getParameter("password");
			authenticValidation(login, pass);
			user = userDAO.findLogInUser(login, pass);
		} catch (SQLException | EmptyFieldException e) {
			request.setAttribute("errmsg", e.getMessage());
			logger.info("Nobody was found");
			request.getRequestDispatcher("authentic.jsp").forward(request, response);
		}
		s.setAttribute("user", user);
		s.setAttribute("app", app);
		if(user.getRoleId() == 1) {
			logger.info("User authentification success");
			s.setAttribute("currPage", "useraccount");
		}else if (user.getRoleId() == 2 ) {
			logger.info("Administrator authentification success");
			s.setAttribute("currPage", "adminaccount");
		}
		request.getRequestDispatcher("/changeLocale").forward(request, response);
	}
	
	private void authenticValidation(String login, String pass) throws EmptyFieldException {
		if("".equals(login) || login == null || login.trim().length() == 0) {
			throw new EmptyFieldException("Login field should not be emty");
		}else if("".equals(pass) || pass == null || pass.trim().length() == 0) {
			throw new EmptyFieldException("Password field should not be emty");
		}
	}

}
