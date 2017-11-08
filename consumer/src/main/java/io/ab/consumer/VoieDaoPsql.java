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
	
	VoieDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Voie> findAllBySecteur(int id) {
		List<Voie> voies = new ArrayList<Voie>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT id, name, length, point_number, cotation, description FROM voie WHERE secteur_id = ?;");
            preparedStatement.setInt(1, id);
			results = preparedStatement.executeQuery();

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
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return voies;
	}
	
	@Override
	public Voie findOne(int id) {
		Voie voie = new Voie();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM voie WHERE id = ?;");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			
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
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return voie;
	}
	
	@Override
	public List<Entity> findEntitiesByName(String name) {
		List<Entity> entities = new ArrayList<Entity>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT id, name FROM voie WHERE UPPER(name) LIKE UPPER(?);");
			preparedStatement.setString(1, "%" + name + "%");
			results = preparedStatement.executeQuery();
			
			while (results.next()) {

        		Entity entity = new Entity();
        		entity.setId(results.getInt("id"));
        		entity.setName(results.getString("name"));;

        		entities.add(entity);
			}
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entities;
	}
	
	@Override
	public List<Entity> findEntitiesByCotation(String cotation) {
		List<Entity> entities = new ArrayList<Entity>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT id, name FROM voie WHERE UPPER(cotation) LIKE UPPER(?);");
			preparedStatement.setString(1, "%" + cotation + "%");
			results = preparedStatement.executeQuery();
			
			while (results.next()) {

        		Entity entity = new Entity();
        		entity.setId(results.getInt("id"));
        		entity.setName(results.getString("name"));;

        		entities.add(entity);
			}
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entities;
	}
	
	@Override
	public void deleteOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM voie WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void deleteBySecteur(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM voie WHERE secteur_id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
