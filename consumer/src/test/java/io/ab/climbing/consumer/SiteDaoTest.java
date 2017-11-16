package io.ab.climbing.consumer;

import java.util.List;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.impl.psql.OwnerDaoPsql;
import io.ab.climbing.consumer.impl.psql.SiteDaoPsql;
import io.ab.climbing.model.Site;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SiteDaoTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public SiteDaoTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SiteDaoTest.class);
	}

	public void testfindAll() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load the DB driver
		BoneCPDataSource ds = new BoneCPDataSource();
	 	ds.setJdbcUrl("jdbc:postgresql://localhost:5432/climbing");		// set the JDBC url
		ds.setUsername("dbuser");				// set the username
		ds.setPassword("dbuser");
		ds.setMaxConnectionsPerPartition(10);
		ds.setMinConnectionsPerPartition(5);
		ds.setPartitionCount(1);
		SiteDaoPsql siteDao = new SiteDaoPsql();
		siteDao.setDataSource(ds);

		List<Site> sites = siteDao.findAll();

		assertTrue(sites.get(0).getName() != null);
		assertTrue(sites.get(0).getHowToFind() != null);
		assertTrue(sites.get(0).getId() != null);
		assertTrue(sites.get(0).getLatitude() != null);
		assertTrue(sites.get(0).getLongitude() != null);
		assertTrue(sites.get(0).getDescription() != null);
		ds.close();
	}

	public void testfindOne() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load the DB driver
		BoneCPDataSource ds = new BoneCPDataSource();
	 	ds.setJdbcUrl("jdbc:postgresql://localhost:5432/climbing");		// set the JDBC url
		ds.setUsername("dbuser");				// set the username
		ds.setPassword("dbuser");
		ds.setMaxConnectionsPerPartition(10);
		ds.setMinConnectionsPerPartition(5);
		ds.setPartitionCount(1);
		SiteDaoPsql siteDao = new SiteDaoPsql();
		siteDao.setDataSource(ds);;

		Site site = siteDao.findOne(1);

		assertTrue(site.getName() != null);
		assertTrue(site.getHowToFind() != null);
		assertTrue(site.getId() != null);
		assertTrue(site.getLatitude() != null);
		assertTrue(site.getLongitude() != null);
		assertTrue(site.getDescription() != null);
		
		ds.close();
	}
}
