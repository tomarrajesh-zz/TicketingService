package com.webservice.ticketingservice.dao;

import java.util.List;

import com.webservice.ticketingservice.model.Activity;

public interface ActivityDao {
	
	void registerActivity(Activity activity);
	
	List<Activity> getActivities(int ticketId);
}
