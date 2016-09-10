package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusDaoImplTest extends EntityDaoImplTest {

	@Autowired
	StatusDao statusDao;
	
	@Test
	public void getStatusesTest() {
		System.out.println("statuses: " + statusDao.getStatuses());
		Assert.assertEquals(statusDao.getStatuses().size(), 3);
	}


}
