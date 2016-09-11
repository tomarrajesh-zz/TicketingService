package com.webservice.ticketingservice.service;

import java.util.Date;
import java.util.List;

import com.webservice.ticketingservice.model.Ticket;

public interface TicketService {
	
	//getTicket
	Ticket getTicket(int ticketId);
	
	//search Tickets
	List<Ticket> getTicketsPaginatedAndFiltered(Integer statusId, Integer severityId, Integer requesterId,
			Integer assigneeId, Date creationDate, Date lastUpdatedDate, Integer pageNumber, Integer pageSize);
	
	void createTicket(Ticket ticket);
	
	void updateTicket(Ticket ticket);
}
