package io.ab.climbing.consumer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Named
public class DaoFactory {

	private static final String URL = "jdbc:postgresql://localhost:5432/climbing";
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbuser";
	private static final int MIN_CONNECTION = 5;
	private static final int MAX_CONNECTION = 10;
	private static final int PARTITION_COUNT = 1;

	@Inject
	private CommentDao commentDao;
	@Inject
	private LongueurDao longueurDao;
	@Inject
	private OwnerDao ownerDao;
	@Inject
	private SecteurDao secteurDao;
	@Inject
	private SiteDao siteDao;
	@Inject
	private TopoDao topoDao;
	@Inject
	private VoieDao voieDao;
	
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

	//<-- Getters
	public CommentDao getCommentDao() {
		return commentDao;
	}

	public LongueurDao getLongueurDao() {
		return longueurDao;
	}

	public OwnerDao getOwnerDao() {
		return ownerDao;
	}

	public SecteurDao getSecteurDao() {
		return secteurDao;
	}

	public SiteDao getSiteDao() {
		return siteDao;
	}

	public TopoDao getTopoDao() {
		return topoDao;
	}

	public VoieDao getVoieDao() {
		return voieDao;
	}
	//Getters -->
}