package com.webservice.ticketingservice.service;

import java.util.Map;

import com.webservice.ticketingservice.model.Status;

public interface StatusService {
	
	Status getStatusById(int id);
	Map<Integer, Status> getStatuses();
}
