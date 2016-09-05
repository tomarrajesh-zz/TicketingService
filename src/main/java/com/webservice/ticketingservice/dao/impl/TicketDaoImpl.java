package com.webservice.ticketingservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.webservice.ticketingservice.dao.AbstractDao;
import com.webservice.ticketingservice.dao.TicketDao;
import com.webservice.ticketingservice.model.Severity;
import com.webservice.ticketingservice.model.Status;
import com.webservice.ticketingservice.model.Ticket;
import com.webservice.ticketingservice.model.User;

@Repository("ticketDao")
public class TicketDaoImpl extends AbstractDao<Integer, Ticket> implements TicketDao {

	@Override
	public void createTicket(Ticket ticket) {
		persist(ticket);
	}

	@Override
	public void updateTicket(Ticket ticket) {
		update(ticket);
	}

	@Override
	public Ticket getTicketByTitle(String title) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("title", title));
		return (Ticket) criteria.uniqueResult();
	}

	@Override
	public Ticket getTicketById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllTickets() {
		Criteria criteria = createEntityCriteria();
		return (List<Ticket>) criteria.list();
	}
	@Override
	public List<Ticket> getTicketsPaginatedAndFiltered(Integer id, String title, Status status, Severity severity,
			User requester, User assignee) {
		Criteria criteria = createEntityCriteria();
		
		if(id != null)	{
			criteria.add(Restrictions.eq("id", id));
		}
		if(title != null) {
			criteria.add(Restrictions.eq("title", title));
		}
		if(status != null) {
			criteria.add(Restrictions.eq("status.id", status.getId()));
		}
		if(severity != null) {
			criteria.add(Restrictions.eq("severity.id", severity.getId()));
		}
		if(requester != null) {
			criteria.add(Restrictions.eq("requester.id", requester.getId()));
		}
		if(assignee != null) {
			criteria.add(Restrictions.eq("assignee.id", assignee.getId()));
		}
		List<Ticket> tickets = criteria.list(); 
		return tickets; 
	}

	
}
