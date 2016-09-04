package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusDaoImplTest extends EntityDaoImplTest {

	@Autowired
	StatusDao statusDao;
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Status.xml"));
		return dataSet;
	}
	
	
	@Test
	public void getStatusesTest() {
		Assert.assertEquals(statusDao.getStatuses().size(), 3);
	}


}
