package com.github.bigquery.dashboard.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.BigQuery;
import com.github.bigquery.dashboard.model.Dashboard;
import com.github.bigquery.dashboard.service.BigQueryService;
import com.github.bigquery.dashboard.service.DashboardService;
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

    @GET
    @Path("/queries/all")
    public List<BigQuery> getBigQueriesFull() {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("List");
            return BigQueryService.listAll();
        }
        return null;
    }

    @PUT
    @Path("/query")
    public BigQuery addQuery(BigQuery bigQuery) {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if (user != null) {
            LOGGER.info("Put " + bigQuery.getLabel());
            return BigQueryService.createOrUpdate(bigQuery);
        }
        return null;

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

    @GET
    @Path("/dashboard/{id}")
    public Dashboard getDashboard(@PathParam("id") Long id) {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("Get " + id);
            return DashboardService.get(id);
        }
        return null;
    }

    @GET
    @Path("/dashboard/user/{id}")
    public List<Dashboard> getDashboards(@PathParam("id") String id) {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("List");
            if(user.getIsAdmin()){
                return DashboardService.list();
            }else {
                return DashboardService.list(id);
            }
        }
        return null;
    }

    @PUT
    @Path("/dashboard")
    public Dashboard addDashboard(Dashboard dashboard) {
        final AppUser user = ServletUtils.getUserAuthenticated();
        if (user != null) {
            LOGGER.info("Put " + dashboard.getLabel());
            return DashboardService.createOrUpdate(dashboard);
        }
        return null;

    }

    @DELETE
    @Path("/dashboard/{id}")
    public void deleteDashboard(@PathParam("id") Long id){
        final AppUser user = ServletUtils.getUserAuthenticated();
        if(user != null){
            LOGGER.info("Delete " + id);
            DashboardService.delete(id);
        }

    }

}
