package com.webservice.ticketingservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.webservice.ticketingservice.aop.Auditable;
import com.webservice.ticketingservice.aop.EventType;
import com.webservice.ticketingservice.iotypes.Activities;
import com.webservice.ticketingservice.iotypes.Activity;
import com.webservice.ticketingservice.iotypes.Correspondences;
import com.webservice.ticketingservice.iotypes.CreateTicketRequest;
import com.webservice.ticketingservice.iotypes.SearchTicketsRequest;
import com.webservice.ticketingservice.iotypes.SearchTicketsResponse;
import com.webservice.ticketingservice.iotypes.UpdateTicketRequest;
import com.webservice.ticketingservice.marshall.IOMarshaller;
import com.webservice.ticketingservice.model.Correspondence;
import com.webservice.ticketingservice.model.Ticket;
import com.webservice.ticketingservice.model.User;
import com.webservice.ticketingservice.service.ActivityService;
import com.webservice.ticketingservice.service.CorrespondenceService;
import com.webservice.ticketingservice.service.SeverityService;
import com.webservice.ticketingservice.service.StatusService;
import com.webservice.ticketingservice.service.TicketService;
import com.webservice.ticketingservice.service.UserService;

@RestController
@Transactional
public class ServiceController {

	@Autowired
	TicketService ticketService;
	@Autowired
	CorrespondenceService correspondenceService;
	@Autowired
	UserService userService;
	@Autowired
	StatusService statusService;
	@Autowired
	SeverityService severityService;
	@Autowired
	ActivityService activityService;

	/*------------------------------------  GET TICKET ------------------------------------*/

	@RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.webservice.ticketingservice.iotypes.Ticket> getTicket(@PathVariable("id") int id) {
		Ticket ticket = ticketService.getTicket(id);
		if (ticket == null) {
			return new ResponseEntity<com.webservice.ticketingservice.iotypes.Ticket>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<com.webservice.ticketingservice.iotypes.Ticket>(IOMarshaller.getOutputTicket(ticket),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/ticket/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchTicketsResponse> searchTickets(@RequestBody SearchTicketsRequest searchRequest) {
		//TODO: validate input
		List<Ticket> tickets = ticketService.getTicketsPaginatedAndFiltered(searchRequest.getStatusId(),
				searchRequest.getSeverityId(), searchRequest.getRequesterId(), searchRequest.getAssigneeId(),
				searchRequest.getCreationDate(), searchRequest.getLastUpdatedDate(), searchRequest.getPageNumber(),
				searchRequest.getPageSize());
		return new ResponseEntity<SearchTicketsResponse>(IOMarshaller.getSearchTicketsResponse(tickets), HttpStatus.OK);
	}

	/*------------------------------------  CREATE TICKET ------------------------------------*/

	
	@RequestMapping(value = "/ticket/new", method = RequestMethod.POST)
	public ResponseEntity<Integer> createTicket(@RequestBody CreateTicketRequest ticketRequest) {
		// TODO: validate input
		System.out.println("Creating Ticket: " + ticketRequest);
		Ticket ticket = createTicketToPersist(ticketRequest);
		ticketService.createTicket(ticket);

		return new ResponseEntity<Integer>(ticket.getId(), HttpStatus.CREATED);
	}

	/*------------------------------------  UPDATE TICKET ------------------------------------*/
	@Auditable(eventType = EventType.TicketUpdated)
	@RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
	public ResponseEntity<com.webservice.ticketingservice.iotypes.Ticket> updateTicket(@PathVariable("id") int ticketId,
			@RequestBody UpdateTicketRequest ticketRequest) {
		// TODO: validate input
		System.out.println("Updating Ticket: " + ticketId);

		Ticket currentTicket = ticketService.getTicket(ticketId);
		System.out.println("old ticket: " + currentTicket);
		if (currentTicket == null) {
			System.out.println("Ticket with id " + ticketId + " not found");
			return new ResponseEntity<com.webservice.ticketingservice.iotypes.Ticket>(HttpStatus.NOT_FOUND);
		}

		currentTicket.setAssignee(userService.getUserById(ticketRequest.getAssigneeId()));
		currentTicket.setSeverity(severityService.getSeverityById(ticketRequest.getSeverityId()));
		currentTicket.setStatus(statusService.getStatusById(ticketRequest.getStatusId()));
		currentTicket.setRootCause(ticketRequest.getRootCause());
		currentTicket.setResolution(ticketRequest.getResolution());
		System.out.println("new to be persisted:" + currentTicket);
		ticketService.updateTicket(currentTicket);
		return new ResponseEntity<com.webservice.ticketingservice.iotypes.Ticket>(
				IOMarshaller.getOutputTicket(currentTicket), HttpStatus.OK);
	}

	/*------------------------------------  GET CORRESPONDENCES ------------------------------------*/

	@RequestMapping(value = "/ticket/{id}/correspondences", method = RequestMethod.GET)
	@Auditable(eventType = EventType.CorrespondenceAskedFor)
	public ResponseEntity<Correspondences> getCorrespondences(@PathVariable("id") int ticketId) {
		// TODO: validate input
		Ticket ticket = ticketService.getTicket(ticketId);
		if (ticket == null) {
			System.out.println("Ticket with id " + ticketId + " not found");
			return new ResponseEntity<Correspondences>(HttpStatus.NOT_FOUND);
		}

		List<Correspondence> correspondences = correspondenceService.getCorrespondencesForTicket(ticket);
		return new ResponseEntity<Correspondences>(IOMarshaller.getOutputCorrespondences(ticket, correspondences),
				HttpStatus.OK);
	}

	/*------------------------------------  ADD CORRESPONDENCE ------------------------------------*/
	@RequestMapping(value = "/ticket/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> addCorrespondence(@PathVariable("id") int ticketId,
			@RequestBody com.webservice.ticketingservice.iotypes.Correspondence correspondence) {
		// TODO: validate input

		Ticket ticket = ticketService.getTicket(correspondence.getTicketId());
		User correspondent = userService.getUserById(correspondence.getUserId());

		if (ticket == null || correspondent == null || ticketId != correspondence.getTicketId()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		Correspondence correspondenceToPersist = new Correspondence();
		correspondenceToPersist.setTicket(ticket);
		correspondenceToPersist.setCorrespondent(correspondent);
		correspondenceToPersist.setContent(correspondence.getContent());
		correspondenceService.addComment(correspondenceToPersist);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/ticket/{id}/activity", method = RequestMethod.GET)
	public ResponseEntity<Activities> getActivities(@PathVariable("id") int ticketId) {
		// TODO: validate input
		List<com.webservice.ticketingservice.model.Activity> activities = activityService.getActivities(ticketId);
		return new ResponseEntity<Activities>(IOMarshaller.getOutputActivities(activities), HttpStatus.OK);
	}
	private Ticket createTicketToPersist(CreateTicketRequest ticketRequest) {
		Ticket ticket = new Ticket();
		ticket.setTitle(ticketRequest.getTitle());
		ticket.setDescription(ticketRequest.getDescription());
		ticket.setAssignee(userService.getUserById(ticketRequest.getAssigneeId()));
		ticket.setRequester(userService.getUserById(ticketRequest.getRequesterId()));
		ticket.setSeverity(severityService.getSeverityById(ticketRequest.getSeverityId()));
		ticket.setStatus(statusService.getStatusById(ticketRequest.getStatusId()));
		System.out.println("ticket data to create: " + ticket);
		return ticket;
	}

}
