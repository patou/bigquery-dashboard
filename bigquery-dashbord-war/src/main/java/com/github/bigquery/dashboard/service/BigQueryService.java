package com.github.bigquery.dashboard.service;

import java.util.List;
import java.util.Map;

import com.github.bigquery.dashboard.model.AbstractQueryParam;
import com.github.bigquery.dashboard.model.BigQuery;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Saver;

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
        return OfyService.ofy().load().group(BigQuery.WithParams.class).key(Key.create(BigQuery.class, itemid)).now();
    }
    
    public static BigQuery createOrUpdate(final BigQuery item) {
        return OfyService.ofy().transact(new Work<BigQuery>() {
            @Override
            public BigQuery run() {
                Saver saver = OfyService.ofy().save();
                Key<BigQuery> bigqueryKey = saver.entity(item).now();
                List<AbstractQueryParam> params = item.getParams();
                if (params != null && params.size() > 0) {
                    for (AbstractQueryParam param : params) {
                        param.setBigQuery(bigqueryKey);
                    }
                    Map<Key<AbstractQueryParam>, AbstractQueryParam> keys = saver.entities(params).now(); //Save Params
                    item.setKeyParams(keys.keySet());
                    saver.entity(item).now(); //Save refs
                }
                return item;
            }
        });
    }
}