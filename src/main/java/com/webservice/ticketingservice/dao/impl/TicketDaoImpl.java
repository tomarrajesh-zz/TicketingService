package com.webservice.ticketingservice.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
	public List<Ticket> getTicketsPaginatedAndFiltered(Integer id, Integer statusId, Integer severityId,
			Integer requesterId, Integer assigneeId, Date creationDate, Date lastUpdatedDate, Integer pageNumber,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria();
		if(id != null)	{
			criteria.add(Restrictions.eq("id", id));
		}
		
		if(statusId != null) {
			criteria.add(Restrictions.eq("status.id", statusId));
		}
		if(severityId != null) {
			criteria.add(Restrictions.eq("severity.id", severityId));
		}
		if(requesterId != null) {
			criteria.add(Restrictions.eq("requester.id", requesterId));
		}
		if(assigneeId != null) {
			criteria.add(Restrictions.eq("assignee.id", assigneeId));
		}
		if(creationDate != null) {
			criteria.add(Restrictions.ge("creationDate", creationDate));
		}
		if(lastUpdatedDate != null) {
			criteria.add(Restrictions.le("lastUpdatedDate", lastUpdatedDate));
		}
		if(pageNumber != null && pageSize != null) {
			criteria.setFirstResult(pageNumber).setMaxResults(pageSize);	
		}
		
		criteria.addOrder(Order.asc("creationDate"));
		List<Ticket> tickets = criteria.list(); 
		return tickets;
	}

	
}
