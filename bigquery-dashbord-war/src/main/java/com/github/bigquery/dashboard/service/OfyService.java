package com.github.bigquery.dashboard.service;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.ToDoItem;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public final class OfyService {
    static {
    	factory().register(AppUser.class);
    	factory().register(ToDoItem.class);
    }
    
    private OfyService() {}
    
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
    
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}