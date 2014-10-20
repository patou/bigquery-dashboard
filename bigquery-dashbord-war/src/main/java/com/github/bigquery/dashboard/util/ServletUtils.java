package com.github.bigquery.dashboard.util;

import java.io.BufferedReader;
import java.io.IOException;

import com.github.bigquery.dashboard.model.AppUser;
import com.github.bigquery.dashboard.service.AppUserService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class ServletUtils {
	private ServletUtils() { }

	private static Gson gson = new Gson();
	private static Gson customGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static String toCustomJson(Object o) {
		return customGson.toJson(o);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
	
	public static <T> T fromCustomJson(String json, Class<T> clazz) {
		return customGson.fromJson(json, clazz);
	}

	private static String getString(BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
	    	String line = reader.readLine();
	    	if (line != null) {
	    		sb.append(line);
		    	while ((line = reader.readLine()) != null)
		    		sb.append('\n').append(line);
	    	}
	    }
		finally {
	        reader.close();
	    }
		return sb.toString();
	}
	
	public static <T> T fromJson(BufferedReader reader, Class<T> clazz) throws IOException {
	    return fromJson(getString(reader), clazz);
	}
	
	public static <T> T fromCustomJson(BufferedReader reader, Class<T> clazz) throws IOException {
	    return fromCustomJson(getString(reader), clazz);
	}
	
	public static AppUser getUserAuthenticated() {
	    final UserService userService = UserServiceFactory.getUserService();
	    final User user = userService.getCurrentUser();
	    
	    return AppUserService.getAppUser(user);
	}
}
