package com.github.bigquery.dashboard.service;

import com.github.bigquery.dashboard.model.Dashboard;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Saver;

import java.util.List;

public final class DashboardService {
    private DashboardService() {}
    
    public static List<Dashboard> list() {
        return OfyService.ofy().load().type(Dashboard.class).list();
    }

    public static List<Dashboard> list(String user) {
        return OfyService.ofy().load().type(Dashboard.class).filter("user =", user).list();
    }
    
    public static void delete(Long id) {
    	OfyService.ofy().delete().key(Key.create(Dashboard.class, id)).now();
    }

    public static Dashboard get(Long id) {
        return OfyService.ofy().load().key(Key.create(Dashboard.class, id)).now();
    }
    
    public static Dashboard createOrUpdate(final Dashboard item) {
        return OfyService.ofy().transact(new Work<Dashboard>() {
            @Override
            public Dashboard run() {
                final Saver saver = OfyService.ofy().save();
                saver.entities(item).now();
                return item;
            }
        });
    }
}