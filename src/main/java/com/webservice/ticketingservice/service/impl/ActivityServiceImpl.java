package com.webservice.ticketingservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservice.ticketingservice.dao.ActivityDao;
import com.webservice.ticketingservice.model.Activity;
import com.webservice.ticketingservice.service.ActivityService;


@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired ActivityDao activityDao;
	
	@Override
	public void registerActivity(Activity activity) {
		activityDao.registerActivity(activity);
	}

	@Override
	public List<Activity> getActivities(int ticketId) {
		return activityDao.getActivities(ticketId);
	}

}
