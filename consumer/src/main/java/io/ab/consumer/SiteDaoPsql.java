package io.ab.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Entity;
import io.ab.model.Site;

public class SiteDaoPsql implements SiteDao {

	private DaoFactory daoFactory;

	SiteDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Site> findAll() {
		List<Site> sites = new ArrayList<Site>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery("SELECT * FROM site;");

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
		return sites;
	}

	@Override
	public Site findOne(int id) {
		Site site = new Site();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM site WHERE id = ?;");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();

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
		return site;
	}

	@Override
	public List<Entity> findEntitiesByName(String name) {
		List<Entity> entities = new ArrayList<Entity>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT id, name FROM site WHERE UPPER(name) LIKE UPPER(?)");
			preparedStatement.setString(1, "%" + name + "%");
			results = preparedStatement.executeQuery();

			while (results.next()) {

				Entity entity = new Entity();
				entity.setId(results.getInt("id"));
				entity.setName(results.getString("name"));
				;

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
			preparedStatement = connection.prepareStatement(
					"SELECT DISTINCT site.id, site.name FROM site INNER JOIN secteur ON (site.id = secteur.site_id) INNER JOIN voie ON (secteur.id = voie.secteur_id) WHERE UPPER(voie.cotation) LIKE UPPER(?);");
			preparedStatement.setString(1, "%" + cotation + "%");
			results = preparedStatement.executeQuery();

			while (results.next()) {

				Entity entity = new Entity();
				entity.setId(results.getInt("id"));
				entity.setName(results.getString("name"));
				;

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
	public int createOne(String name) {
		int id = -1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO site (name) VALUES (?) RETURNING id");
			preparedStatement.setString(1, name);
			results = preparedStatement.executeQuery();

			results.next();

			id = results.getInt(1);

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
		return id;
	}

}
