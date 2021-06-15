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
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.dao.daoEntities.UsersActivityDAO;
import com.my.project.dao.daoEntities.UsersDAO;
import com.my.project.model.Activity;
import com.my.project.model.Category;
import com.my.project.model.Users;
import com.my.project.security.SecurityFilter;

@WebServlet("/selectedUser")
public class SelectedUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(SelectedUserServlet.class.getName());

    public SelectedUserServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Info about user");
		UsersActivityDAO userActivityDAO = DAOFactory.getUsersActivityDAO("mysql");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		UsersDAO userDAO = DAOFactory.getUserDAO("mysql");
		Users user = (Users) request.getSession().getAttribute("user");
		Users currentUser = new Users();
		Map<Activity, Users> userUserActivities = new HashMap<Activity, Users>();
		List<Category> categories = new ArrayList<>();
		int activitiesCount = 0;
		try {
			categories = categoryDAO.getAllCategories();
			currentUser = userDAO.findUserById(Integer.parseInt(request.getParameter("currentUser")));
			activitiesCount = userActivityDAO.countAllUserActsById(currentUser.getId());
			userUserActivities = userActivityDAO.getAllUserActByIdMap(currentUser.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		request.setAttribute("user", user);
		request.setAttribute("categories", categories);
		request.setAttribute("userActivities", userUserActivities);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("count", activitiesCount);
		SecurityFilter.isAdminFilter(request, response, "WEB-INF/view/selectedUser.jsp");
	}
}
