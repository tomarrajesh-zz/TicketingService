package com.webservice.ticketingservice.service;

import java.util.List;

import com.webservice.ticketingservice.model.Activity;

public interface ActivityService {
	
	void registerActivity(Activity activity);
	
	List<Activity> getActivities(int ticketId);
}
