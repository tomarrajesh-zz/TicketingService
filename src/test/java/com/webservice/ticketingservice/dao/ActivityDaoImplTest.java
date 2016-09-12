package com.webservice.ticketingservice.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.webservice.ticketingservice.aop.EventType;
import com.webservice.ticketingservice.aop.SubEventType;
import com.webservice.ticketingservice.model.Activity;

public class ActivityDaoImplTest extends EntityDaoImplTest {
	
	@Autowired ActivityDao activityDao;
	@Autowired TicketDao ticketDao;
	
	@Test
	public void registerActivityTest() {
		Activity activity = getSampleActivity();
		activityDao.registerActivity(activity);
	}

	@Test
	public void getActivitiesTest() {
		Assert.assertEquals(activityDao.getActivities(1).size(), 1);
		Assert.assertEquals(activityDao.getActivities(3).size(), 0);
	}
	
	private Activity getSampleActivity() {
		Activity activity = new Activity();
		activity.setTicket(ticketDao.getTicketById(3));
		activity.setEvent(EventType.TicketCreated);
		activity.setSubEvent(SubEventType.AssigneeChanged);
		activity.setDescription("dummy desc");
		activity.setActivityDate(new Date());
		return activity;
	}
}
