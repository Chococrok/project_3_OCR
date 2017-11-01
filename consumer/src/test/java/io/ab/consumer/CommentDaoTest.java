package io.ab.consumer;

import java.sql.Timestamp;
import java.util.List;

import io.ab.model.Comment;
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
		CommentDao commentDao = DaoFactory.getInstance().getCommentDao();

		List<Comment> comments = commentDao.findAllBy(Comment.SITE_ID, 1);

		assertTrue(comments.get(0).getContent() != null);
		assertTrue(comments.get(0).getId() != null);
		assertTrue(comments.get(0).getTimestamp() != null);
	}

	public void testAddOne() {
		CommentDao commentDao = DaoFactory.getInstance().getCommentDao();

		commentDao.addOneBy(Comment.SITE_ID, 1, "toto et tatta", new Timestamp(System.currentTimeMillis()));
	}
}
