package com.github.bigquery.dashboard.servlet;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bigquery.dashboard.util.ServletUtils;

public class GetUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.getWriter().println(ServletUtils.toJson(ServletUtils.getUserAuthenticated()));
	}
}
