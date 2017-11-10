package io.ab.consumer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import io.ab.consumer.impl.psql.CommentDaoPsql;
import io.ab.consumer.impl.psql.LongueurDaoPsql;
import io.ab.consumer.impl.psql.OwnerDaoPsql;
import io.ab.consumer.impl.psql.SecteurDaoPsql;
import io.ab.consumer.impl.psql.SiteDaoPsql;
import io.ab.consumer.impl.psql.TopoDaoPsql;
import io.ab.consumer.impl.psql.VoieDaoPsql;

@WebListener
public class DaoFactory implements ServletContextListener {

	private static final String URL = "jdbc:postgresql://localhost:5432/climbing";
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbuser";
	private static final int MIN_CONNECTION = 5;
	private static final int MAX_CONNECTION = 10;
	private static final int PARTITION_COUNT = 1;

	public static final String ATT_DAO_FACTORY = "daoFactory";
	
	private BoneCP connectionPool;
	
	//Test purpose
	private static DaoFactory testDaoFactory;

	public DaoFactory() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}
		
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USERNAME); 
		config.setPassword(PASSWORD);
		config.setMinConnectionsPerPartition(MIN_CONNECTION);
		config.setMaxConnectionsPerPartition(MAX_CONNECTION);
		config.setPartitionCount(PARTITION_COUNT);
		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		
		/* Enregistrement dans un attribut ayant pour portée toute l'application */

		servletContext.setAttribute(ATT_DAO_FACTORY, new DaoFactory());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		this.connectionPool.shutdown();
	}

	//test purpose
	public static DaoFactory getInstance() {
		if (testDaoFactory == null) {
			testDaoFactory = new DaoFactory();
		}
		return testDaoFactory;
	}
	
	public static void shutdownCP() {
		testDaoFactory.connectionPool.shutdown();
	}

	public Connection getConnection() throws SQLException {
		return this.connectionPool.getConnection();
	}

	public SiteDao getSiteDao() {
		return new SiteDaoPsql(this);
	}

	public CommentDao getCommentDao() {
		return new CommentDaoPsql(this);
	}

	public SecteurDao getSecteurDao() {
		return new SecteurDaoPsql(this);
	}

	public VoieDao getVoieDao() {
		return new VoieDaoPsql(this);
	}

	public LongueurDao getLongueurDao() {
		return new LongueurDaoPsql(this);
	}

	public TopoDao getTopoDao() {
		return new TopoDaoPsql(this);
	}

	public OwnerDao getOwnerDao() {
		return new OwnerDaoPsql(this);
	}
}