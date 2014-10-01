package com.github.bigquery.dashboard.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.BigQuery;
import com.github.bigquery.dashboard.service.BigQueryService;
import com.github.bigquery.dashboard.service.OfyService;
import com.github.bigquery.dashboard.util.ServletUtils;

import static com.google.common.base.Strings.isNullOrEmpty;

@SuppressWarnings("serial")
public class BigQueryServlet extends HttpServlet {
    Logger logger = Logger.getLogger(BigQueryServlet.class.getName());
	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Create
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
	    BigQuery item = ServletUtils.fromJson(req.getReader(), BigQuery.class);
	    if (user != null && item != null) {
	    	resp.setContentType("application/json");
            logger.info("Put " + item.getLibelle());
	    	resp.getWriter().println(ServletUtils.toCustomJson(BigQueryService.createOrUpdate(item)));
	    }
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Retrieve
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
        if (user != null) {
            resp.setContentType("application/json");
            if (!isNullOrEmpty(req.getPathInfo())) {
                Long id = Long.parseLong(req.getPathInfo().substring(1));
                logger.info("Get " + id);
                resp.getWriter().println(ServletUtils.toCustomJson(BigQueryService.get(id)));
            }
		    else {
                logger.info("List");
                resp.getWriter().println(ServletUtils.toCustomJson(BigQueryService.list()));
            }
		}
	}

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Delete

        AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
        resp.setContentType("application/json");
        if (user != null && !isNullOrEmpty(req.getPathInfo())) {
            Long id = Long.parseLong(req.getPathInfo().substring(1));
            logger.info("Delete " + id);
            BigQueryService.delete(id);
        }
    }
}
