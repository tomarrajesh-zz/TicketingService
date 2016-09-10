package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.webservice.ticketingservice.model.Correspondence;

public class CorrespondenceDaoImplTest extends EntityDaoImplTest {

	@Autowired CorrespondenceDao correspondenceDao;
	
	@Autowired TicketDao ticketDao;
	
	@Autowired UserDao userDao;
	
//	@Override
//	protected IDataSet getDataSet() throws Exception {
//	  IDataSet[] datasets = new IDataSet[] {
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Status.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Severity.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Ticket.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Correspondence.xml"))
//	  };
//	  return new CompositeDataSet(datasets);
//	}
	
	@Test
	public void createCorrespondenceTest() {
		Correspondence correspondence = getSampleCorrespondence();
		correspondenceDao.createCorrespondence(correspondence);
		
		Assert.assertEquals(correspondenceDao.getCorrespondencesForTicket(ticketDao.getTicketById(3)).size(), 1);
	}

	@Test
	public void getCorrespondencesForTicketTest() {
		Assert.assertEquals(correspondenceDao.getCorrespondencesForTicket(ticketDao.getTicketById(1)).size(), 5);
		
		Assert.assertEquals(correspondenceDao.getCorrespondencesForTicket(ticketDao.getTicketById(3)).size(), 0);
	}
	
	private Correspondence getSampleCorrespondence() {
		Correspondence correspondence = new Correspondence();
		correspondence.setContent("The service is down");
		correspondence.setTicket(ticketDao.getTicketById(3));
		correspondence.setCorrespondent(userDao.getUserById(1));
		return correspondence;
	}

}
