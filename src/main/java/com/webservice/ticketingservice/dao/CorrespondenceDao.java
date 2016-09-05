package com.webservice.ticketingservice.dao;

import java.util.List;

import com.webservice.ticketingservice.model.Correspondence;
import com.webservice.ticketingservice.model.Ticket;

public interface CorrespondenceDao {
	
	void createCorrespondence(Correspondence correspondence);
	
	List<Correspondence> getCorrespondencesForTicket(Ticket ticket);
}
