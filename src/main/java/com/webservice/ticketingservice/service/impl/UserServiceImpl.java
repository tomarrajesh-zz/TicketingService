package com.webservice.ticketingservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.ticketingservice.dao.UserDao;
import com.webservice.ticketingservice.model.User;
import com.webservice.ticketingservice.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired UserDao userDao;
	
	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByHandle(String handle) {
		return userDao.getUserByHandle(handle);
	}

}
