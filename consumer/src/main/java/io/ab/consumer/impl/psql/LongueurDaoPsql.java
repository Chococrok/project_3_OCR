package io.ab.consumer.impl.psql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.LongueurDao;
import io.ab.model.Longueur;
import io.ab.model.Voie;

public class LongueurDaoPsql implements LongueurDao {

	private DaoFactory daoFactory;

	public LongueurDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Longueur findOne(int id) {
		Longueur longueur = new Longueur();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM longueur WHERE id = ?;");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();

			result.next();

			longueur.setId(result.getInt("id"));
			longueur.setName(result.getString("name"));
			longueur.setLength(result.getInt("length"));
			longueur.setCotation(result.getString("cotation"));
			Voie voie = new Voie();
			voie.setId(result.getInt("voie_id"));
			longueur.setVoie(voie);

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
		return longueur;
	}

	public List<Longueur> findAllByVoie(int id) {
		List<Longueur> longueurs = new ArrayList<Longueur>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;

		try {
			connection = this.daoFactory.getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery(
					String.format("SELECT id, name, length, cotation FROM longueur WHERE voie_id = %d;", id));

			while (results.next()) {

				Longueur longueur = new Longueur();
				longueur.setId(results.getInt("id"));
				longueur.setName(results.getString("name"));
				longueur.setLength(results.getInt("length"));
				longueur.setCotation(results.getString("cotation"));

				longueurs.add(longueur);
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
		return longueurs;
	}

	@Override
	public void deleteOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM longueur WHERE id = ?");
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
	public void deleteByVoie(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM longueur WHERE voie_id = ?");
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
	public void insertOne(Longueur longueur) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO longueur (name, cotation, length, voie_id) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, longueur.getName());
			preparedStatement.setString(2, longueur.getCotation());
			preparedStatement.setInt(3, longueur.getLength());
			preparedStatement.setInt(4, longueur.getVoie().getId());
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
	public void updateOne(Longueur longueur) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"UPDATE longueur SET name = ?, cotation = ?, length = ?  WHERE id = ?");
			preparedStatement.setString(1, longueur.getName());
			preparedStatement.setString(2, longueur.getCotation());
			preparedStatement.setInt(3, longueur.getLength());
			preparedStatement.setInt(4, longueur.getId());
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
