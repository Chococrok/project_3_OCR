package io.ab.consumer;

import java.util.List;

import io.ab.model.Topo;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OwnerDaoTest extends TestCase {

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OwnerDaoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( OwnerDaoTest.class );
    }
    
    public void testExists()
    {
		OwnerDao ownerDao = DaoFactory.getInstance().getOwnerDao();
		
		boolean exists = ownerDao.exists("jm@lfi.com");
        assertTrue( exists );
        
        exists = ownerDao.exists("toto@tata.com");
        assertFalse( exists );
    }
    
    public void testMatch()
    {
		OwnerDao ownerDao = DaoFactory.getInstance().getOwnerDao();
		
		boolean match = ownerDao.checkPassword("jm@lfi.com", "insoumis");
        assertTrue( match );
        
        match = ownerDao.checkPassword("jm@lfi.com", "toto");
        assertFalse( match );
    }
}
