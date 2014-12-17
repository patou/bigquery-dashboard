package com.github.bigquery.dashboard.rest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.BigQuery;
import com.github.bigquery.dashboard.service.BigQueryService;
import com.github.bigquery.dashboard.service.ExecuteBigQueryService;
import com.github.bigquery.dashboard.util.ServletUtils;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.JobReference;

@Path("/execute")
@Produces(MediaType.APPLICATION_JSON)
public class ExecuteRestService {
    private static final Logger LOGGER = Logger.getLogger(ExecuteRestService.class.getName());

    @POST
    @Path("/query")
    public JobReference execute(String query) throws GeneralSecurityException, IOException {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("Execute " + query);
            return ExecuteBigQueryService.startQuery(query);
        }
        return null;
    }

    @POST
    @Path("/query/try")
    public String tryExecute(String query) throws GeneralSecurityException, IOException {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("Execute " + query);
            return ExecuteBigQueryService.tryQuery(query);
        }
        return null;
    }

    @GET
    @Path("/result/{job_id}")
    public GetQueryResultsResponse getResult(@PathParam("job_id") String jobId) throws GeneralSecurityException, IOException {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if (user != null) {
            LOGGER.info("Get Result  " + jobId);
            return ExecuteBigQueryService.getResult(jobId);
        }
        return null;
    }
}
