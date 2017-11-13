package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.impl.psql.SiteDaoPsql;
import io.ab.climbing.model.Site;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SiteDaoTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SiteDaoTest( String testName )
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

    
    public void testfindAll()
    {
    		SiteDaoPsql siteDao = new SiteDaoPsql();
    		siteDao.setDaoFactory(new DaoFactory());
    		
    		List<Site> sites = siteDao.findAll();
    		
        assertTrue( sites.get(0).getName() != null );
        assertTrue( sites.get(0).getHowToFind() != null );
        assertTrue( sites.get(0).getId() != null );
        assertTrue( sites.get(0).getLatitude() != null );
        assertTrue( sites.get(0).getLongitude() != null );
        assertTrue( sites.get(0).getDescription() != null );
    }
    
    public void testfindOne()
    {
    	SiteDaoPsql siteDao = new SiteDaoPsql();
		siteDao.setDaoFactory(new DaoFactory());
		
		Site site = siteDao.findOne(1);
    		
        assertTrue( site.getName() != null );
        assertTrue( site.getHowToFind() != null );
        assertTrue( site.getId() != null );
        assertTrue( site.getLatitude() != null );
        assertTrue( site.getLongitude() != null );
        assertTrue( site.getDescription() != null );
    }
}
