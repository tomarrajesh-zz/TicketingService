package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SeverityDaoImplTest extends EntityDaoImplTest {

	@Autowired
	SeverityDao severityDao;
	
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
	public void getSeveritiesTest() {
		System.out.println("sevs: " + severityDao.getSeverities());
		Assert.assertEquals(severityDao.getSeverities().size(), 3);
	}
	

}
