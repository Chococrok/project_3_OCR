package io.ab.climbing.consumer;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.impl.psql.CommentDaoPsql;
import io.ab.climbing.model.Comment;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CommentDaoTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public CommentDaoTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CommentDaoTest.class);
	}

	public void testFindAllBySite() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	// load the DB driver
		BoneCPDataSource ds = new BoneCPDataSource();
	 	ds.setJdbcUrl("jdbc:postgresql://localhost:5432/climbing");		// set the JDBC url
		ds.setUsername("dbuser");				// set the username
		ds.setPassword("dbuser");
		ds.setMaxConnectionsPerPartition(10);
		ds.setMinConnectionsPerPartition(5);
		ds.setPartitionCount(1);
		CommentDaoPsql commentDao = new CommentDaoPsql();
		commentDao.setDataSource(ds);

		List<Comment> comments = commentDao.findAllBy(Comment.SITE_ID, 1);

		assertTrue(comments.get(0).getContent() != null);
		assertTrue(comments.get(0).getId() != null);
		assertTrue(comments.get(0).getTimestamp() != null);
		
		ds.close();
	}

	public void testAddOne() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	// load the DB driver
		BoneCPDataSource ds = new BoneCPDataSource();
	 	ds.setJdbcUrl("jdbc:postgresql://localhost:5432/climbing");		// set the JDBC url
		ds.setUsername("dbuser");				// set the username
		ds.setPassword("dbuser");
		ds.setMaxConnectionsPerPartition(10);
		ds.setMinConnectionsPerPartition(5);
		ds.setPartitionCount(1);
		CommentDaoPsql commentDao = new CommentDaoPsql();
		commentDao.setDataSource(ds);
		
		commentDao.addOneBy(Comment.SITE_ID, 1, "toto et tatta", new Timestamp(System.currentTimeMillis()));
	
		ds.close();
	}
}
