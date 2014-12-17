package com.github.bigquery.dashboard.rest;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<GoogleJsonResponseException> {
 
    public Response toResponse(GoogleJsonResponseException ex) {
        GoogleJsonError details = ex.getDetails();
        return Response.status(details.getCode())
                .entity(details.getMessage())
                .type(MediaType.APPLICATION_JSON).
                build();
    }
 
}