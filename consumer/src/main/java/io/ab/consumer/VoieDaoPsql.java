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
import io.ab.model.Voie;

public class VoieDaoPsql implements VoieDao {
	
	private DaoFactory daoFactory;
	
	public VoieDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public List<Voie> findAllBySecteur(int id) {
		List<Voie> voies = new ArrayList<Voie>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT id, name, length, point_number, cotation, description FROM voie WHERE secteur_id = %d;", id));

            while (results.next()) {

            		Voie voie = new Voie();
            		voie.setId(results.getInt("id"));
            		voie.setName(results.getString("name"));
            		voie.setDescription(results.getString("description"));
            		voie.setLength(results.getInt("length"));
            		voie.setPointNumber(results.getInt("point_number"));
            		voie.setCotation(results.getString("cotation"));
 
	            voies.add(voie);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voies;
	}
	
	public Voie findOne(int id) {
		Voie voie = new Voie();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(String.format("SELECT * FROM voie WHERE id = %d;", id));
			
			result.next();
                
			voie.setId(result.getInt("id"));
	    		voie.setName(result.getString("name"));
	    		voie.setDescription(result.getString("description"));
	    		voie.setLength(result.getInt("length"));
	    		voie.setPointNumber(result.getInt("point_number"));
	    		voie.setCotation(result.getString("cotation"));
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voie;
	}
	
	public List<Entity> findEntitiesByName(String name) {
		List<Entity> entities = new ArrayList<Entity>();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM voie WHERE UPPER(name) LIKE UPPER(?);");
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
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM voie WHERE UPPER(cotation) LIKE UPPER(?);");
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
