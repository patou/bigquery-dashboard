package com.github.bigquery.dashboard.service;

import java.util.List;

import com.github.bigquery.dashboard.model.BigQuery;
import com.googlecode.objectify.Key;

public final class BigQueryService {
    private BigQueryService() {}
    
    public static List<BigQuery> list() {
        List<BigQuery> list = OfyService.ofy().load().type(BigQuery.class).list();
        return list;
    }

    public static List<BigQuery> listAll() {
        List<BigQuery> list = OfyService.ofy().load().type(BigQuery.class).list();
        return list;
    }

    public static List<BigQuery> list(String libelle) {
        return OfyService.ofy().load().type(BigQuery.class).filter("label >=", libelle).filter("label <", libelle + "\uFFFD").list();
    }
    
    public static void delete(Long itemid) {
    	OfyService.ofy().delete().key(Key.create(BigQuery.class, itemid)).now();
    }

    public static BigQuery get(Long itemid) {
        return OfyService.ofy().load().key(Key.create(BigQuery.class, itemid)).now();
    }
    
    public static BigQuery createOrUpdate(BigQuery item) {
    	OfyService.ofy().save().entity(item).now();
    	return item;
    }
}