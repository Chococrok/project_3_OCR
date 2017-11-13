package io.ab.climbing.consumer;

import java.util.List;

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
    	OwnerDaoPsql ownerDao = new OwnerDaoPsql();
    	ownerDao.setDaoFactory(new DaoFactory());	
    	
		boolean exists = ownerDao.exists("jm@lfi.com");
        assertTrue( exists );
        
        exists = ownerDao.exists("toto@tata.com");
        assertFalse( exists );
    }
    
    public void testMatch()
    {
    	OwnerDaoPsql ownerDao = new OwnerDaoPsql();
    	ownerDao.setDaoFactory(new DaoFactory());	
    	
		boolean match = ownerDao.checkPassword("jm@lfi.com", "insoumis");
        assertTrue( match );
        
        match = ownerDao.checkPassword("jm@lfi.com", "toto");
        assertFalse( match );
    }
}
