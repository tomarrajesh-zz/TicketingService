package com.webservice.ticketingservice.service;

import java.util.Map;

import com.webservice.ticketingservice.model.Severity;

public interface SeverityService {
	
	Severity getSeverityById(int id);
	Map<Integer, Severity> getSeverities();
}
