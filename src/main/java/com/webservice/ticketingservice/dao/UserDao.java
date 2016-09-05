package com.webservice.ticketingservice.dao;

import java.util.List;

import com.webservice.ticketingservice.model.User;

public interface UserDao {
	
	void createUser(User user);
	
	void updateUser(User user);
	
	User getUserByHandle(String name);
	
	User getUserById(int id);
	
	List<User> getUsers();
}
