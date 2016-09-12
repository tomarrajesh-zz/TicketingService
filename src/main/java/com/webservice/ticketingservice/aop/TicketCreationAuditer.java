package com.webservice.ticketingservice.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webservice.ticketingservice.model.Activity;
import com.webservice.ticketingservice.model.Ticket;
import com.webservice.ticketingservice.service.ActivityService;

@Component
class TicketCreationAuditer implements DoAudit {

	@Autowired ActivityService activityService;
	
	@Override
	public void audit(Object[] requestArgs) {
		Ticket ticket = (Ticket)requestArgs[0];
		Activity activity = getActivity(ticket);
		activityService.registerActivity(activity);
	}

	private Activity getActivity(Ticket ticket) {
		
		Activity activity = new Activity();
		activity.setEvent(EventType.TicketCreated);
		System.out.println("ticket : " + ticket);
		activity.setTicket(ticket);
		activity.setDescription("Ticket created by: " + ticket.getRequester().getId());
		
		return activity;
	}

}
