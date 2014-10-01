package com.github.bigquery.dashboard.service;

import com.github.bigquery.dashboard.model.AppUser;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Objectify;

public final class AppUserService {    
    private AppUserService() {}
    
    public static AppUser getAppUser(User user) {
    	Objectify ofy = OfyService.ofy();
    	
    	AppUser appUser = ofy.load().type(AppUser.class).id(user.getUserId()).now();
        UserService userService = UserServiceFactory.getUserService();
    	if (appUser == null) { // appUser wasn't in the datastore
    		appUser = new AppUser(user.getUserId(), user.getEmail());
            appUser.setIsAdmin(userService.isUserAdmin());
    		ofy.save().entity(appUser).now();
    	}
    	else { // appUser is already in the datastore
    		
    		// update properties if they've changed
    		if (!appUser.getEmail().equalsIgnoreCase(user.getEmail()) || userService.isUserAdmin() != appUser.getIsAdmin()) {
    			appUser.setEmail(user.getEmail());
                appUser.setIsAdmin(userService.isUserAdmin());
    			ofy.save().entity(appUser).now();
    		}
    	}
    	
        return appUser;
    }
}