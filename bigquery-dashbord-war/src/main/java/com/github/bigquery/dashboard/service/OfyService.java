package com.github.bigquery.dashboard.service;

import com.github.bigquery.dashboard.model.AbstractQueryParam;
import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.BigQuery;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public final class OfyService {
    static {
    	factory().register(AppUser.class);
    	factory().register(BigQuery.class);
        factory().register(AbstractQueryParam.TextQueryParam.class);
        factory().register(AbstractQueryParam.DateQueryParam.class);
        factory().register(AbstractQueryParam.ListQueryParam.class);
        factory().register(AbstractQueryParam.NumberQueryParam.class);
        factory().register(AbstractQueryParam.class);
    }
    
    private OfyService() {}
    
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
    
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
