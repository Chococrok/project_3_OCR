package io.ab.consumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
    private String url;
    private String username;
    private String password;
    
    private static DaoFactory DAOFACTORY_INSTANCE = null;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
    		if (DAOFACTORY_INSTANCE == null) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	
	        }
	
	        DAOFACTORY_INSTANCE = new DaoFactory(
	                "jdbc:postgresql://localhost:5432/climbing", "dbuser", "dbuser");
    		}
        return DAOFACTORY_INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
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
}