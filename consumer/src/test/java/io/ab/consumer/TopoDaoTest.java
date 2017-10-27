package io.ab.consumer;

import java.util.List;

import io.ab.model.Topo;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TopoDaoTest extends TestCase {

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TopoDaoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TopoDaoTest.class );
    }
    
    public void testfindAll()
    {
		TopoDao topoDao = DaoFactory.getInstance().getTopoDao();
		
		List<Topo> topos = topoDao.findAll();
    		
        assertTrue( topos.get(0).getName() != null );
        assertTrue( topos.get(0).getSite().getId() == 1 );
        assertTrue( topos.get(0).getId() != null );
    }
}
