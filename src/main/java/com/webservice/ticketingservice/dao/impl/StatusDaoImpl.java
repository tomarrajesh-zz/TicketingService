package com.webservice.ticketingservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.webservice.ticketingservice.dao.AbstractDao;
import com.webservice.ticketingservice.dao.StatusDao;
import com.webservice.ticketingservice.model.Status;

@Repository("statusDao")
public class StatusDaoImpl extends AbstractDao<Integer, Status> implements StatusDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getStatuses() {
		Criteria criteria = createEntityCriteria();
		return (List<Status>) criteria.list();
	}
}
