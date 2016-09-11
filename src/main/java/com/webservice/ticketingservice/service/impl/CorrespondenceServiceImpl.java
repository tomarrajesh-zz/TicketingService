package com.webservice.ticketingservice.service.impl;

import java.util.List;

import com.webservice.ticketingservice.dao.CorrespondenceDao;
import com.webservice.ticketingservice.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.webservice.ticketingservice.model.Correspondence;
import com.webservice.ticketingservice.service.CorrespondenceService;

@Service("correspondenceService")
public class CorrespondenceServiceImpl implements CorrespondenceService {
	
	@Autowired CorrespondenceDao correspondenceDao;
	
	public void addComment(Correspondence correspondence) {
		correspondenceDao.createCorrespondence(correspondence);
	}
	
	public List<Correspondence> getCorrespondencesForTicket(Ticket ticket) {
		return correspondenceDao.getCorrespondencesForTicket(ticket);
	}
}
