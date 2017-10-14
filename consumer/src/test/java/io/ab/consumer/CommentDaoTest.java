package io.ab.consumer;

import java.util.List;

import io.ab.model.Comment;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CommentDaoTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CommentDaoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SiteDaoTest.class );
    }

    
    public void testFindAllBySite()
    {
    		CommentDao siteDao = DaoFactory.getInstance().getCommentDao();
    		
    		List<Comment> comments = siteDao.findAllBySite(2);
    		
        assertTrue( comments.get(0).getContent() != null );
    }
}
