package com.github.bigquery.dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.ToDoItem;
import com.github.bigquery.dashboard.service.OfyService;
import com.github.bigquery.dashboard.util.ServletUtils;

@SuppressWarnings("serial")
public class DeleteToDoItemServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Delete
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
	    ToDoItem item = ServletUtils.fromCustomJson(req.getReader(), ToDoItem.class);
	    
	    if (user != null && item != null) {
	    	item.setOwner(user);
	    	OfyService.ofy().delete().entity(item).now();
	    }
	}
}