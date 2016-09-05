package com.webservice.ticketingservice.dao;

import java.util.List;

import com.webservice.ticketingservice.model.Severity;
import com.webservice.ticketingservice.model.Status;
import com.webservice.ticketingservice.model.Ticket;
import com.webservice.ticketingservice.model.User;

public interface TicketDao {
	
	void createTicket(Ticket ticket);
	
	void updateTicket(Ticket ticket);
	
	Ticket getTicketByTitle(String title);
	
	Ticket getTicketById(int id);
	
	List<Ticket> getAllTickets();
	List<Ticket> getTicketsPaginatedAndFiltered(Integer id, String title, Status status
			, Severity severity, User requester, User assignee);
}
