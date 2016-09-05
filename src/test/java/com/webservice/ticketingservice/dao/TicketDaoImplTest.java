package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
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
	
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Status.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Severity.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Ticket.xml"))			  
	  };
	  return new CompositeDataSet(datasets);
	}
	
	@Test
	public void createTicketTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);

		Assert.assertEquals(ticketDao.getTicketById(ticket.getId()), ticket);
		System.out.println(ticketDao.getAllTickets());
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
		System.out.println(ticketDao.getAllTickets());
	}

	@Test
	public void getTicketByTitleTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);
		
		Assert.assertNotNull(ticketDao.getTicketByTitle(ticket.getTitle()));
		System.out.println(ticketDao.getAllTickets());
	}
	
	@Test
	public void getTicketByIdTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);
		
		Assert.assertNotNull(ticketDao.getTicketById(ticket.getId()));
		System.out.println(ticketDao.getAllTickets());
	}
	
	@Test
	public void getTicketByIdNonExistantIdTest() {
		Ticket ticket = getSampleTicket();
		ticketDao.createTicket(ticket);
		Assert.assertNull(ticketDao.getTicketById(100));
		System.out.println(ticketDao.getAllTickets());
	}
	
	@Test
	public void getAllTickets() {
		System.out.println(ticketDao.getAllTickets());
	}
	
	/**
	 * Get all tickets and compare with no filters
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest1() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, null, null).size(), ticketDao.getAllTickets().size());
	}
	
	/**
	 * Check working with individual id
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest2() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(1, null, null, null, null, null).size(), 1);
	}
	
	/**
	 * Check working with individual title
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest3() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, "service_down", null, null, null, null).size(), ticketDao.getAllTickets().size());
	}
	
	/**
	 * Check working with individual assignee
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest4() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, null, userDao.getUserById(1)).size(), ticketDao.getAllTickets().size());
	}
	
	/**
	 * Check working with individual requester
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest5() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, userDao.getUserById(1), null).size(), 6);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, null, userDao.getUserById(2), null).size(), 6);
	}
	
	/**
	 * Check working with individual status
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest6() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(0), null, null, null).size(), 4);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(1), null, null, null).size(), 4);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(2), null, null, null).size(), 4);
	}
	
	/**
	 * Check working with individual severity
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest7() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, severityDao.getSeverities().get(0), null, null).size(), 6);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, severityDao.getSeverities().get(1), null, null).size(), 6);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, null, severityDao.getSeverities().get(2), null, null).size(), 0);
	}
	
	/**
	 * Check working with few random values of severity, requester and status 
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest8() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(0), severityDao.getSeverities().get(0), userDao.getUserById(1), null).size(), 1);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(1), severityDao.getSeverities().get(1), userDao.getUserById(1), null).size(), 1);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(1), severityDao.getSeverities().get(2), userDao.getUserById(2), null).size(), 0);
	}
	
	/**
	 * Check working with few random values of severity, assignee and status 
	 */
	@Test
	public void getTicketsPaginatedAndFilteredTest9() {
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(0), severityDao.getSeverities().get(0), null, userDao.getUserById(1)).size(), 2);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(0), severityDao.getSeverities().get(2), null, userDao.getUserById(1)).size(), 0);
		Assert.assertEquals(ticketDao.getTicketsPaginatedAndFiltered(null, null, statusDao.getStatuses().get(0), severityDao.getSeverities().get(0), null, userDao.getUserById(2)).size(), 0);
	}
	
	private Ticket getSampleTicket() {
		Ticket ticket = new Ticket();
		ticket.setTitle("service-down");
		
		byte[] description = "The service is down".getBytes();
		ticket.setDescription(description);
		
		ticket.setStatus(statusDao.getStatuses().get(0));
		ticket.setSeverity(severityDao.getSeverities().get(0));
		
		ticket.setRequester(userDao.getUserById(2));
		ticket.setAssignee(userDao.getUserById(1));
		return ticket;
	}
	

}
