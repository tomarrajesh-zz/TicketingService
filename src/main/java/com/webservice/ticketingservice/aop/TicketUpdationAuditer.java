package com.webservice.ticketingservice.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webservice.ticketingservice.iotypes.UpdateTicketRequest;
import com.webservice.ticketingservice.model.Activity;
import com.webservice.ticketingservice.service.ActivityService;
import com.webservice.ticketingservice.service.TicketService;

@Component
class TicketUpdationAuditer implements DoAudit {

	@Autowired ActivityService activityService;
	@Autowired TicketService ticketService;
	
	@Override
	public void audit(Object[] requestArgs) {
		int ticketId = (Integer)requestArgs[0];
		UpdateTicketRequest updateTicketRequest = (UpdateTicketRequest)requestArgs[1];
		Activity activity = new Activity();
		activity.setTicket(ticketService.getTicket(ticketId));
		activity.setEvent(EventType.TicketUpdated);
		
		activity.setDescription("Ticket updated!");
		
		List<Activity> activities = new ArrayList<>();
		
		if(updateTicketRequest.getAssigneeId() != 0) {
			Activity newActivity = copyActivity(activity);
			newActivity.setSubEvent(SubEventType.AssigneeChanged);
			activities.add(newActivity);
		}
		if(updateTicketRequest.getSeverityId() != 0) {
			Activity newActivity = copyActivity(activity);
			newActivity.setSubEvent(SubEventType.SeverityChanged);
			activities.add(newActivity);
		}
		if(updateTicketRequest.getStatusId() != 0) {
			Activity newActivity = copyActivity(activity);
			newActivity.setSubEvent(SubEventType.StatusChanged);
			activities.add(newActivity);
		}
		if(updateTicketRequest.getRootCause() != null) {
			Activity newActivity = copyActivity(activity);
			newActivity.setSubEvent(SubEventType.RootCauseAddedOrChanged);
			activities.add(newActivity);
		}
		if(updateTicketRequest.getResolution() != null) {
			Activity newActivity = copyActivity(activity);
			newActivity.setSubEvent(SubEventType.ResolutionAddedOrChanged);
			activities.add(newActivity);
		}
		
		for(Activity theActivity : activities) {
			activityService.registerActivity(theActivity);	
		}
	}
	
	private Activity copyActivity(Activity activity) {
		Activity newActivity = new Activity();
		newActivity.setId(activity.getId());
		newActivity.setTicket(activity.getTicket());
		newActivity.setEvent(activity.getEvent());
		newActivity.setDescription(activity.getDescription());
		newActivity.setActivityDate(activity.getActivityDate());
		
		return newActivity;
	}
}
