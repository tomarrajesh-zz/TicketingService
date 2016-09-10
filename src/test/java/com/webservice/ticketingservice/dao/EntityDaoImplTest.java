package com.webservice.ticketingservice.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.webservice.ticketingservice.configuration.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	DataSource dataSource;

	@BeforeMethod
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
				dataSource);
		ITableFilter sequenceFilter = new DatabaseSequenceFilter(dbConn);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, new FilteredDataSet(sequenceFilter, getDataSet()));
		//DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}
	
	//@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Status.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Severity.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Ticket.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Correspondence.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
}