package com.webservice.ticketingservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.ticketingservice.aop.Auditable;
import com.webservice.ticketingservice.aop.EventType;
import com.webservice.ticketingservice.dao.TicketDao;
import com.webservice.ticketingservice.model.Ticket;
import com.webservice.ticketingservice.service.TicketService;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

	@Autowired TicketDao ticketDao;

	@Override
	public Ticket getTicket(int ticketId) {
		return ticketDao.getTicketById(ticketId);
	}

	@Override
	@Auditable(eventType = EventType.TicketCreated)
	public void createTicket(Ticket ticket) {
		ticketDao.createTicket(ticket);
	}

	@Override
	public void updateTicket(Ticket ticket) {
		ticketDao.updateTicket(ticket);
	}

	@Override
	public List<Ticket> getTicketsPaginatedAndFiltered(Integer statusId, Integer severityId, Integer requesterId,
			Integer assigneeId, Date creationDate, Date lastUpdatedDate, Integer pageNumber, Integer pageSize) {
		return ticketDao.getTicketsPaginatedAndFiltered(null, statusId, severityId, requesterId, assigneeId,
				creationDate, lastUpdatedDate, pageNumber, pageSize);
	}

}
