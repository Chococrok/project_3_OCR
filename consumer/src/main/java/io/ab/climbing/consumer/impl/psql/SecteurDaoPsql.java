package io.ab.climbing.consumer.impl.psql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import io.ab.climbing.consumer.SecteurDao;
import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Secteur;
import io.ab.climbing.model.Site;
import io.ab.climbing.model.Voie;

@Named
public class SecteurDaoPsql  extends AbstractDaoPsql implements SecteurDao {

	@Override
	public List<Secteur> findAllBySite(int id) {
		List<Secteur> secteurs = new ArrayList<Secteur>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.createStatement();
            results = statement.executeQuery(String.format("SELECT id, name, description FROM secteur where site_id = %d;", id));

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
		} finally {
			try {
				connection.close();
				statement.close();
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return secteurs;
	}
	
	@Override
	public Secteur findOne(int id) {
		Secteur secteur = new Secteur();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(String.format("SELECT * FROM secteur WHERE id = %d;", id));
			
			result.next();
                
            secteur.setName(result.getString("name"));
            secteur.setId(result.getInt("id"));
            secteur.setDescription(result.getString("description"));
            Site site = new Site();
            site.setId(result.getInt("site_id"));
            secteur.setSite(site);
                
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return secteur;
	}
	
	@Override
	public List<Entity> findEntitiesByName(String name) {
		List<Entity> entities = new ArrayList<Entity>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.dataSource.getConnection();
			preparedStatement = connection.prepareStatement("SELECT id, name FROM secteur WHERE UPPER(name) LIKE UPPER(?);");
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
			connection = this.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT DISTINCT secteur.id, secteur.name FROM secteur INNER JOIN voie ON (secteur.id = voie.secteur_id) WHERE UPPER(voie.cotation) LIKE UPPER(?);");
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
			connection = this.dataSource.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM secteur WHERE id = ?");
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
	public void deleteBySite(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.dataSource.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM secteur WHERE site_id = ?");
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
	public void updateOne(Secteur secteur) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					"UPDATE secteur SET name = ?, description = ?  WHERE id = ?");
			preparedStatement.setString(1, secteur.getName());
			preparedStatement.setString(2, secteur.getDescription());
			preparedStatement.setInt(3, secteur.getId());
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
	public void insertOne(Secteur secteur) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO secteur (name, description, site_id) VALUES (?, ?, ?)");
			preparedStatement.setString(1, secteur.getName());
			preparedStatement.setString(2, secteur.getDescription());
			preparedStatement.setInt(3, secteur.getSite().getId());
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
