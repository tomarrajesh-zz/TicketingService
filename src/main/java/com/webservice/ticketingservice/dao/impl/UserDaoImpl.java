package com.webservice.ticketingservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.webservice.ticketingservice.dao.AbstractDao;
import com.webservice.ticketingservice.dao.UserDao;
import com.webservice.ticketingservice.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public void createUser(User user) {
		persist(user);
	}

	@Override
	public void updateUser(User user) {
		update(user);
	}

	@Override
	public User getUserByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (User) criteria.uniqueResult();
	}

	@Override
	public User getUserById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

}
