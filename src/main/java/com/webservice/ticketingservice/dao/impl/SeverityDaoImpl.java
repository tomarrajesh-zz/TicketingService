package com.webservice.ticketingservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.webservice.ticketingservice.dao.AbstractDao;
import com.webservice.ticketingservice.dao.SeverityDao;
import com.webservice.ticketingservice.model.Severity;

@Repository("severityDao")
public class SeverityDaoImpl extends AbstractDao<Integer, Severity> implements SeverityDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Severity> getSeverities() {
		Criteria criteria = createEntityCriteria();
		return (List<Severity>) criteria.list();
	}
}
