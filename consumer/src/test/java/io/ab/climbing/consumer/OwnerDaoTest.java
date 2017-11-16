package io.ab.climbing.consumer;

import java.util.List;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.impl.psql.CommentDaoPsql;
import io.ab.climbing.consumer.impl.psql.OwnerDaoPsql;
import io.ab.climbing.model.Topo;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OwnerDaoTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public OwnerDaoTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(OwnerDaoTest.class);
	}

	public void testExists() {
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
		
		OwnerDaoPsql ownerDao = new OwnerDaoPsql();
		ownerDao.setDataSource(ds);

		boolean exists = ownerDao.exists("jm@lfi.com");
		assertTrue(exists);

		exists = ownerDao.exists("toto@tata.com");
		assertFalse(exists);
		
		ds.close();
	}

	public void testMatch() {
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
		OwnerDaoPsql ownerDao = new OwnerDaoPsql();
		ownerDao.setDataSource(ds);

		boolean match = ownerDao.checkPassword("jm@lfi.com", "insoumis");
		assertTrue(match);

		match = ownerDao.checkPassword("jm@lfi.com", "toto");
		assertFalse(match);
		
		ds.close();
	}
}
