package io.ab.climbing.consumer;

import java.sql.Timestamp;
import java.util.List;

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
		CommentDaoPsql commentDao = new CommentDaoPsql();
		commentDao.setDaoFactory(new DaoFactory());

		List<Comment> comments = commentDao.findAllBy(Comment.SITE_ID, 1);

		assertTrue(comments.get(0).getContent() != null);
		assertTrue(comments.get(0).getId() != null);
		assertTrue(comments.get(0).getTimestamp() != null);
	}

	public void testAddOne() {
		CommentDaoPsql commentDao = new CommentDaoPsql();
		commentDao.setDaoFactory(new DaoFactory());
		
		commentDao.addOneBy(Comment.SITE_ID, 1, "toto et tatta", new Timestamp(System.currentTimeMillis()));
	}
}
