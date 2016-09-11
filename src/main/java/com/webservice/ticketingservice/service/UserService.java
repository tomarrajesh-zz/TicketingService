package com.webservice.ticketingservice.service;

import com.webservice.ticketingservice.model.User;

public interface UserService {
	
	User getUserById(int id);
	
	User getUserByHandle(String handle);
}
