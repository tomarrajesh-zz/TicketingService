package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SeverityDaoImplTest extends EntityDaoImplTest {

	@Autowired
	SeverityDao severityDao;
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Severity.xml"));
		return dataSet;
	}
	
	
	@Test
	public void getSeveritiesTest() {
		Assert.assertEquals(severityDao.getSeverities().size(), 3);
	}
	

}
