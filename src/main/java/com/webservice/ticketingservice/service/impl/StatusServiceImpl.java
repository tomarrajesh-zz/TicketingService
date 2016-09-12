package com.webservice.ticketingservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.ticketingservice.dao.StatusDao;
import com.webservice.ticketingservice.model.Status;
import com.webservice.ticketingservice.service.StatusService;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

	@Autowired StatusDao statusDao;

	private static Map<Integer, Status> statuses = null;
	
	@Override
	public Status getStatusById(int id) {
		if(statuses == null) {
			getStatuses();
		}
		return statuses.get(id);
	}

	@Override
	public Map<Integer, Status> getStatuses() {
		if(statuses == null) synchronized (StatusServiceImpl.class) {
				if(statuses == null) {
					statuses = new HashMap<>();
					for(Status status : statusDao.getStatuses()) {
						statuses.put(status.getId(), status);
					}
				}
		}
		return statuses;
	}
	
}
