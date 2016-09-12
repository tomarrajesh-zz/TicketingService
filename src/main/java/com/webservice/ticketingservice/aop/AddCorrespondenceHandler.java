package com.webservice.ticketingservice.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webservice.ticketingservice.model.Activity;
import com.webservice.ticketingservice.model.Correspondence;
import com.webservice.ticketingservice.service.ActivityService;

@Component
class AddCorrespondenceHandler implements DoAudit {

	@Autowired ActivityService activityService;
	
	@Override
	public void audit(Object[] requestArgs) {
		Correspondence correspondence = (Correspondence) requestArgs[0];
		Activity activity = getActivity(correspondence);
		activityService.registerActivity(activity);
	}
	
	Activity getActivity(Correspondence correspondence) {
		Activity activity = new Activity();
		activity.setEvent(EventType.CorrespondenceAdded);
		activity.setDescription("Correspondence added by user: " + correspondence.getCorrespondent().getId());
		activity.setTicket(correspondence.getTicket());
		return activity;
	}

}
