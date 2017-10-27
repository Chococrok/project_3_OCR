package io.ab.consumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DaoFactory implements ServletContextListener {
	
    private String url;
    private String username;
    private String password;
    
    public static final String  ATT_DAO_FACTORY = "daoFactory";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	ServletContext servletContext = sce.getServletContext();

    	try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

    	DaoFactory daoFactory = new DaoFactory(
                "jdbc:postgresql://localhost:5432/climbing", "dbuser", "dbuser");

        /* Enregistrement dans un attribut ayant pour portée toute l'application */

        servletContext.setAttribute( ATT_DAO_FACTORY, daoFactory );
        servletContext.setAttribute("toto", "tata");
	}
    
    @Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
    
    //default constructor needed by WebListener.
    public DaoFactory (){}

	private DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
	
	public static DaoFactory getInstance() {
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

    	DaoFactory daoFactory = new DaoFactory(
                "jdbc:postgresql://localhost:5432/climbing", "dbuser", "dbuser");
    	return daoFactory;
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
    
    public VoieDao getVoieDao() {
        return new VoieDaoPsql(this);
    }
    
    public LongueurDao getLongueurDao() {
        return new LongueurDaoPsql(this);
    }
    
    public TopoDao getTopoDao() {
    	return new TopoDaoPsql(this);
    }
}