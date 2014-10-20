package com.github.bigquery.dashboard.servlet;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.BigQuery;
import com.github.bigquery.dashboard.service.BigQueryService;
import com.github.bigquery.dashboard.util.ServletUtils;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class BigQueryRestService {
    private static final Logger LOGGER = Logger.getLogger(BigQueryRestService.class.getName());

    @GET
    @Path("/query/{id}")
    public BigQuery getBigQuery(@PathParam("id") Long id) {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("Get " + id);
            return BigQueryService.get(id);
        }
        return null;
    }

    @GET
    @Path("/queries")
    public List<BigQuery> getBigQueries() {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("List");
            return BigQueryService.list();
        }
        return null;
    }

    @PUT
    @Path("/query")
    public void addQuery( BigQuery bigQuery) {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if (user != null) {
            LOGGER.info("Put " + bigQuery.getLibelle());
            BigQueryService.createOrUpdate(bigQuery);
        }

    }

    @DELETE
    @Path("/query/{id}")
    public void deleteQuery(@PathParam("id") Long id){
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("Delete " + id);
            BigQueryService.delete(id);
        }

    }

}
