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
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.model.Activity;
import com.my.project.model.Category;
import com.my.project.security.SecurityFilter;

@WebServlet("/activities")
public class AdminUserActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(AdminUserActivityServlet.class.getName());

	public AdminUserActivityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Activity controller");
		@SuppressWarnings("unchecked")
		List<Activity> activitiesList = (ArrayList<Activity>) request.getSession().getAttribute("activitiesList");
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO("mysql");
		List<Category> categories = new ArrayList<>();
		int activitiesCount = activitiesList.size();
		int page = Integer.parseInt(request.getParameter("page"));
		logger.info("On page " + page);
		int portion = 5;
		int step = (activitiesCount % portion > 0) ? 1 : 0;
		int pageCount = activitiesCount / portion + step;
		int downLimitPage = 1;
		int upperLimitPage = pageCount;
		try {
			categories = categoryDAO.getAllCategories();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		request.setAttribute("categories", categories);
		request.setAttribute("listLen", activitiesCount);
		request.setAttribute("activitiesList", activitiesList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("page", page);
		request.setAttribute("portion", portion);
		request.setAttribute("downLimitPage", downLimitPage);
		request.setAttribute("upperLimitPage", upperLimitPage);
		SecurityFilter.isAdminFilter(request, response, "WEB-INF/view/activity.jsp");
	}
}
