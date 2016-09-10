package com.webservice.ticketingservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.webservice.ticketingservice.model.Ticket;

public class TicketDaoImplTest extends EntityDaoImplTest {

	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	StatusDao statusDao;
	
	@Autowired
	SeverityDao severityDao;
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void createTicketTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);

		Assert.assertEquals(ticketDao.getTicketById(ticket.getId()), ticket);
	}

	@Test
	public void updateTicketTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);


		ticket.setAssignee(userDao.getUserById(2));
		ticket.setStatus(statusDao.getStatuses().get(1));
		ticket.setSeverity(severityDao.getSeverities().get(1));
		
		ticketDao.updateTicket(ticket);
		Assert.assertEquals(ticketDao.getTicketById(ticket.getId()), ticket);
	}

	@Test
	public void getTicketByTitleTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);
		
		Assert.assertNotNull(ticketDao.getTicketByTitle(ticket.getTitle()));
	}
	
	@Test
	public void getTicketByIdTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);
		
		Assert.assertNotNull(ticketDao.getTicketById(ticket.getId()));
	}
	
	@Test
	public void getTicketByIdNonExistantIdTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);
		Assert.assertNull(ticketDao.getTicketById(100));
	}
	
	@Test
	public void getAllTickets() {
		Assert.assertTrue(ticketDao.getAllTickets().size() == 12);
	}
	
	/**
	 * Get all tickets and compare with no filters
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest1() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, null, null, null, null, null).size(), ticketDao.getAllTickets().size());
	}
	
	/**
	 * Check working with individual id
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest2() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(1, null, null, null, null, null, null, null, null).size(), 1);
	}
	
	/**
	 * Check working with individual title
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest3() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, null, null, null, 0, 5).size(), 5);
	}
	
	/**
	 * Check working with individual assignee
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest4() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, 1, null, null, null, null).size(), ticketDao.getAllTickets().size());
	}
	
	/**
	 * Check working with individual requester
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest5() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, 1, null, null, null, null, null).size(), 6);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, 2, null, null, null, null, null).size(), 6);
	}
	
	/**
	 * Check working with individual status
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest6() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 1, null, null, null, null, null, null, null).size(), 4);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 2, null, null, null, null, null, null, null).size(), 4);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 3, null, null, null, null, null, null, null).size(), 4);
	}
	
	/**
	 * Check working with individual severity
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest7() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, 1, null, null, null, null, null, null).size(), 6);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, 2, null, null, null, null, null, null).size(), 6);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, 3, null, null, null, null, null, null).size(), 0);
	}
	
	/**
	 * Check working with few random values of severity, requester and status 
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest8() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 1, 1, 2, null, null, null, null, null).size(), 1);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 2, 2, 2, null, null, null, null, null).size(), 1);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 2, 3, 3, null, null, null, null, null).size(), 0);
	}
	
	/**
	 * Check working with few random values of severity, assignee and status 
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest9() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 1, 1, null, 1, null, null, null, null).size(), 2);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 1, 3, null, 1, null, null, null, null).size(), 0);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, 1, 1, null, 2, null, null, null, null).size(), 0);
	}
	
	private Ticket getSampleTicket() {
		Ticket ticket = new Ticket();
		ticket.setTitle("service-down");
		
		ticket.setDescription("The service is down");
		
		ticket.setStatus(statusDao.getStatuses().get(0));
		ticket.setSeverity(severityDao.getSeverities().get(0));
		
		ticket.setRequester(userDao.getUserById(2));
		ticket.setAssignee(userDao.getUserById(1));
		return ticket;
	}
	

}
