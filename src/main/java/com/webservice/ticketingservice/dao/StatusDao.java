package com.webservice.ticketingservice.dao;

import java.util.List;

import com.webservice.ticketingservice.model.Status;

public interface StatusDao {
	List<Status> getStatuses();
}
