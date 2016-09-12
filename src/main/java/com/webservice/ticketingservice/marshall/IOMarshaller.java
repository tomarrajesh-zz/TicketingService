package com.webservice.ticketingservice.marshall;

import java.util.ArrayList;
import java.util.List;

import com.webservice.ticketingservice.iotypes.Activities;
import com.webservice.ticketingservice.iotypes.Correspondence;
import com.webservice.ticketingservice.iotypes.Correspondences;
import com.webservice.ticketingservice.iotypes.SearchTicketsResponse;
import com.webservice.ticketingservice.model.Activity;
import com.webservice.ticketingservice.model.Ticket;

public class IOMarshaller {
	
	public static com.webservice.ticketingservice.iotypes.Ticket getOutputTicket(Ticket ticket) {
		com.webservice.ticketingservice.iotypes.Ticket outputTicket = new com.webservice.ticketingservice.iotypes.Ticket();
		outputTicket.setTitle(ticket.getTitle());
		outputTicket.setDescription(ticket.getDescription());
		outputTicket.setAssigneeHandle(ticket.getAssignee().getHandle());
		outputTicket.setRequesterHandle(ticket.getRequester().getHandle());
		outputTicket.setStatus(ticket.getStatus().getStatus());
		outputTicket.setSeverity(ticket.getSeverity().getSeverity());
		outputTicket.setRootCause(ticket.getRootCause());
		outputTicket.setResolution(ticket.getResolution());
		outputTicket.setCreationDate(ticket.getCreationDate());
		outputTicket.setLastUpdateDate(ticket.getLastUpdatedDate());
		return outputTicket;
	}
	
	public static SearchTicketsResponse getSearchTicketsResponse(List<Ticket> tickets) {
		
		List<com.webservice.ticketingservice.iotypes.Ticket> outputTickets = new ArrayList<>();
		for(Ticket ticket : tickets) {
			outputTickets.add(getOutputTicket(ticket));
		}
		
		SearchTicketsResponse response = new SearchTicketsResponse(outputTickets);
		return response;
	}
	
	public static com.webservice.ticketingservice.iotypes.Correspondence getOutputCorrespondence(com.webservice.ticketingservice.model.Correspondence correspondence) {
		return new Correspondence(correspondence.getTicket().getId(), correspondence.getCorrespondent().getId(), 
				correspondence.getContent(), correspondence.getCreationDate(), correspondence.getLastUpdatedDate());
	}
	
	public static Correspondences getOutputCorrespondences(Ticket ticket, List<com.webservice.ticketingservice.model.Correspondence> correspondences) {
		List<com.webservice.ticketingservice.iotypes.Correspondence> modelCorrespondences = new ArrayList<>();
		for(com.webservice.ticketingservice.model.Correspondence correspondence : correspondences) {
			modelCorrespondences.add(getOutputCorrespondence(correspondence));
		}
		
		com.webservice.ticketingservice.iotypes.Ticket outputTicket = getOutputTicket(ticket);
		
		return new Correspondences(outputTicket, modelCorrespondences);
	}

	public static Activities getOutputActivities(List<Activity> activities) {
		List<com.webservice.ticketingservice.iotypes.Activity> outputActivities = new ArrayList<>();
		for(Activity activity : activities) {
			outputActivities.add(getOutputActivity(activity));
		}
		
		return new Activities(outputActivities);
	}

	private static com.webservice.ticketingservice.iotypes.Activity getOutputActivity(Activity activity) {
		com.webservice.ticketingservice.iotypes.Activity outputActivity = new com.webservice.ticketingservice.iotypes.Activity();
		outputActivity.setTicketId(activity.getTicket().getId());
		outputActivity.setEvent(activity.getEvent());
		outputActivity.setSubEvent(activity.getSubEvent());
		return outputActivity;
	}
	
	
}
