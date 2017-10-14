package io.ab.consumer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Site;

public class SiteDaoPsql implements SiteDao {
	
	private DaoFactory daoFactory;
	
	public SiteDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Site> findAll() {
		List<Site> sites = new ArrayList<Site>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM site;");

            while (results.next()) {

                Site site = new Site();
                
                site.setName(results.getString("name"));
                site.setId(results.getInt("id"));
                site.setHowToFind(results.getString("how_to_find"));
                site.setLatitude(results.getBigDecimal("lat"));
                site.setLongitude(results.getBigDecimal("long"));
                site.setDescription(results.getString("description"));
                
                sites.add(site);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sites;
	}

	public Site findOne(int id) {
		Site site = new Site();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(String.format("SELECT * FROM site WHERE id = %d;", id));
			
			result.next();
                
            site.setName(result.getString("name"));
            site.setId(result.getInt("id"));
            site.setHowToFind(result.getString("how_to_find"));
            site.setLatitude(result.getBigDecimal("lat"));
            site.setLongitude(result.getBigDecimal("long"));
            site.setDescription(result.getString("description"));
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return site;
	}

}
