package com.webservice.ticketingservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.webservice.ticketingservice.dao.AbstractDao;
import com.webservice.ticketingservice.dao.CorrespondenceDao;
import com.webservice.ticketingservice.model.Correspondence;
import com.webservice.ticketingservice.model.Ticket;

@Repository("correspondenceDao")
public class CorrespondenceDaoImpl extends AbstractDao<Integer, Correspondence> implements CorrespondenceDao {

	@Override
	public void createCorrespondence(Correspondence correspondence) {
		persist(correspondence);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Correspondence> getCorrespondencesForTicket(Ticket ticket) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ticket.id", ticket.getId()));
		return criteria.list();
	}
	
}
