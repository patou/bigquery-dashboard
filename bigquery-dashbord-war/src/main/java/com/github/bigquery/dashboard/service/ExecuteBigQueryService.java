package com.github.bigquery.dashboard.service;

import com.github.bigquery.dashboard.util.Config;
import com.github.bigquery.dashboard.util.CredentialsUtils;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.JobReference;
import com.google.api.services.bigquery.model.QueryRequest;
import com.google.api.services.bigquery.model.QueryResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 21/10/2014.
 */
public class ExecuteBigQueryService {
    static Logger logger = Logger.getLogger(ExecuteBigQueryService.class.getName());
    public static JobReference startQuery(String query) throws GeneralSecurityException, IOException {
        Bigquery bigquery = CredentialsUtils.getBigquery();
        QueryRequest request = new QueryRequest();
        request.setQuery(query);
        request.setTimeoutMs(0L);
        request.setUseQueryCache(true);
        try {
            QueryResponse result = bigquery.jobs().query(Config.BIGQUERY_PROJECTID, request).execute();
            return result.getJobReference();
        }
        catch (GoogleJsonResponseException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public static String tryQuery(String query) throws GeneralSecurityException, IOException {
        Bigquery bigquery = CredentialsUtils.getBigquery();
        QueryRequest request = new QueryRequest();
        request.setQuery(query);
        request.setDryRun(true);
        try {
            QueryResponse result = bigquery.jobs().query(Config.BIGQUERY_PROJECTID, request).execute();
            return "OK";
        }
        catch (GoogleJsonResponseException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public static GetQueryResultsResponse getResult(String jobId) throws GeneralSecurityException, IOException {
        Bigquery bigquery = CredentialsUtils.getBigquery();
        try {
            GetQueryResultsResponse result = bigquery.jobs().getQueryResults(Config.BIGQUERY_PROJECTID, jobId).execute();
            return result;
        }
        catch (GoogleJsonResponseException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }
}
