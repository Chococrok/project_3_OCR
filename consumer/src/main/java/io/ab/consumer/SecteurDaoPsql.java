package io.ab.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Entity;
import io.ab.model.Secteur;
import io.ab.model.Site;

public class SecteurDaoPsql implements SecteurDao {
	
	private DaoFactory daoFactory;

	SecteurDaoPsql(DaoFactory daoFactory) {
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
	
	public Secteur findOne(int id) {
		Secteur secteur = new Secteur();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(String.format("SELECT * FROM secteur WHERE id = %d;", id));
			
			result.next();
                
            secteur.setName(result.getString("name"));
            secteur.setId(result.getInt("id"));
            secteur.setDescription(result.getString("description"));
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return secteur;
	}
	
	public List<Entity> findEntitiesByName(String name) {
		List<Entity> entities = new ArrayList<Entity>();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM secteur WHERE UPPER(name) LIKE UPPER(?);");
			preparedStatement.setString(1, "%" + name + "%");
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {

        		Entity entity = new Entity();
        		entity.setId(results.getInt("id"));
        		entity.setName(results.getString("name"));;

        		entities.add(entity);
			}
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<Entity> findEntitiesByCotation(String cotation) {
		List<Entity> entities = new ArrayList<Entity>();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT DISTINCT secteur.id, secteur.name FROM secteur INNER JOIN voie ON (secteur.id = voie.secteur_id) WHERE UPPER(voie.cotation) LIKE UPPER(?);");
			preparedStatement.setString(1, "%" + cotation + "%");
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {

        		Entity entity = new Entity();
        		entity.setId(results.getInt("id"));
        		entity.setName(results.getString("name"));;

        		entities.add(entity);
			}
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entities;
	}
}
