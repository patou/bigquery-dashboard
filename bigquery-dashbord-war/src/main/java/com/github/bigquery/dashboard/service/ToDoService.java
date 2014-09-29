package com.github.bigquery.dashboard.service;

import java.util.List;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.model.ToDoItem;

public final class ToDoService {   
    private ToDoService() {}
    
    public static List<ToDoItem> getToDoList(AppUser user) {
    	return OfyService.ofy().load().type(ToDoItem.class).ancestor(user.getKey()).list();
    }
    
    public static void delete(AppUser user, ToDoItem item) {
    	item.setOwner(user);
    	OfyService.ofy().delete().entity(item).now();
    }
    
    public static ToDoItem createOrUpdate(AppUser user, ToDoItem item) {
    	item.setOwner(user);
    	OfyService.ofy().save().entity(item).now();
    	return item;
    }
}