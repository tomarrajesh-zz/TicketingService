package com.webservice.ticketingservice.service;

import java.util.List;

import com.webservice.ticketingservice.model.Correspondence;
import com.webservice.ticketingservice.model.Ticket;

public interface CorrespondenceService {
	
	void addComment(Correspondence correspondence);
	
	List<Correspondence> getCorrespondencesForTicket(Ticket ticket);
}
