package com.github.bigquery.dashboard.util;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.appengine.api.utils.SystemProperty;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.Set;

public class CredentialsUtils {
    static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	static final JacksonFactory JSON_FACTORY = JacksonFactory
			.getDefaultInstance();

	public static Bigquery getBigquery() throws GeneralSecurityException, IOException {
		Set<String> scopes = new HashSet<String>();
		scopes.add(BigqueryScopes.BIGQUERY);
		scopes.add(BigqueryScopes.BIGQUERY_INSERTDATA);
        HttpRequestInitializer credential = getCredential(scopes);
        return new Bigquery.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(Config.APPLICATION_NAME).build();
	}

    private static HttpRequestInitializer getCredential(Set<String> scopes) throws GeneralSecurityException, IOException {
        if (SystemProperty.environment.value().equals(SystemProperty.Environment.Value.Development)) {
            //TODO Dev code
            GoogleCredential credential = null;
            try {
                credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                        .setJsonFactory(JSON_FACTORY)
                                // Obtain this from https://console.developers.google.com/project/myproject/apiui/credential
                        .setServiceAccountId("1098120739370-0255d5mgj6aqpqhjv4briqvtkb102f8t@developer.gserviceaccount.com")
                        .setServiceAccountScopes(scopes)
                                // Download this file from https://console.developers.google.com/project/myproject/apiui/credential
                        .setServiceAccountPrivateKeyFromP12File(new File(CredentialsUtils.class.getResource("dashboard-bigquery-b7688a960ef4.p12").toURI()))

                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return credential;
        }
        return getAppIdentityCredential(scopes);
    }

    private static HttpRequestInitializer getAppIdentityCredential(Set<String> scopes) {
        return new AppIdentityCredential(scopes);
    }
}