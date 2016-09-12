package com.webservice.ticketingservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.webservice.ticketingservice.dao.AbstractDao;
import com.webservice.ticketingservice.dao.ActivityDao;
import com.webservice.ticketingservice.model.Activity;

@Repository("activityDao")
public class ActivityDaoImpl extends AbstractDao<Integer, Activity> implements ActivityDao {

	@Override
	public void registerActivity(Activity activity) {
		persist(activity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivities(int ticketId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ticket.id", ticketId));
		
		return criteria.list();
	}

}
