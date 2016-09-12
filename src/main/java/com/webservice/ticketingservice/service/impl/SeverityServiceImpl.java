package com.webservice.ticketingservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.ticketingservice.dao.SeverityDao;
import com.webservice.ticketingservice.model.Severity;
import com.webservice.ticketingservice.service.SeverityService;

@Service("severityService")
public class SeverityServiceImpl implements SeverityService {

	@Autowired SeverityDao severityDao;
	
	private static Map<Integer, Severity> severityMap = null;
	
	@Override
	public Severity getSeverityById(int id) {
		if(severityMap == null) {
			getSeverities();
		}
		return severityMap.get(id);
	}

	@Override
	public Map<Integer, Severity> getSeverities() {
		if(severityMap == null) synchronized (StatusServiceImpl.class) {
			if(severityMap == null) {
				severityMap = new HashMap<>();
				for(Severity severity : severityDao.getSeverities()) {
					severityMap.put(severity.getId(), severity);
				}
			}
	}
		return severityMap;
	}

}
