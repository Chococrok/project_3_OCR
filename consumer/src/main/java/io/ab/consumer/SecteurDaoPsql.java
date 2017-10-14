package io.ab.consumer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Secteur;

public class SecteurDaoPsql implements SecteurDao {
	
	private DaoFactory daoFactory;

	public SecteurDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Secteur> findAllBySite(int id) {
		List<Secteur> secteurs = new ArrayList<Secteur>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT id, name, description FROM secteur where site_id = %d;", id));

            while (results.next()) {

        			Secteur secteur = new Secteur();
	            secteur.setId(results.getInt("id"));
	            secteur.setName(results.getString("name"));
	            secteur.setDescription(results.getString("description"));
                
                secteurs.add(secteur);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return secteurs;
	}

}
