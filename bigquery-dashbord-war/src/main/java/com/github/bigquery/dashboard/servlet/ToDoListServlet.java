package com.github.bigquery.dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.ToDoItem;
import com.github.bigquery.dashboard.service.ToDoService;
import com.github.bigquery.dashboard.util.ServletUtils;

@SuppressWarnings("serial")
public class ToDoListServlet extends HttpServlet {
	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Create
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
	    ToDoItem item = ServletUtils.fromJson(req.getReader(), ToDoItem.class);
	    if (user != null && item != null) {
	    	resp.setContentType("application/json");
	    	resp.getWriter().println(ServletUtils.toCustomJson(ToDoService.createOrUpdate(user, item)));
	    }
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Retrieve
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
		if (user != null) {
			resp.setContentType("application/json");
			resp.getWriter().println(ServletUtils.toCustomJson(ToDoService.getToDoList(user)));
		}
	}
}