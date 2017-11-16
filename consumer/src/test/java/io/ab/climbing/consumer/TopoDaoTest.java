package io.ab.climbing.consumer;

import java.util.List;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.impl.psql.SiteDaoPsql;
import io.ab.climbing.consumer.impl.psql.TopoDaoPsql;
import io.ab.climbing.model.Topo;
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
    	
		TopoDaoPsql topoDao = new TopoDaoPsql();
    	topoDao.setDataSource(ds);
		
		List<Topo> topos = topoDao.findAll();
    		
        assertTrue( topos.get(0).getName() != null );
        assertTrue( topos.get(0).getSite().getId() == 1 );
        assertTrue( topos.get(0).getId() != null );
        
        ds.close();
    }
}
