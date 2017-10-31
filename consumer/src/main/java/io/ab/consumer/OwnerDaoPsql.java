package io.ab.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Owner;
import io.ab.model.Site;
import io.ab.model.Topo;

public class OwnerDaoPsql implements OwnerDao {

	private DaoFactory daoFactory;

	OwnerDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Owner> findByTopo(int id) {
		List<Owner> owners = new ArrayList<Owner>();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT owner.*, topo_owner.available FROM owner INNER JOIN topo_owner ON (topo_owner.topo_id = ?) WHERE topo_owner.owner_id = owner.id;");
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {

				Owner owner = new Owner();

				owner.setId(results.getInt("id"));
				owner.setFirstName(results.getString("first_name"));
				owner.setLastName(results.getString("last_name"));
				owner.setEmail(results.getString("email"));
				owner.setPhoneNumber(results.getString("phone_number"));
				owner.setTopoAvailable(results.getBoolean("available"));

				owners.add(owner);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return owners;
	}

	@Override
	public Owner findOneById(int id) {
		Owner owner = new Owner();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM owner WHERE id = ?;");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			result.next();

			owner.setId(result.getInt("id"));
			owner.setFirstName(result.getString("first_name"));
			owner.setLastName(result.getString("last_name"));
			owner.setEmail(result.getString("email"));
			owner.setPhoneNumber(result.getString("phone_number"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return owner;
	}
	
	@Override
	public Owner findOneByEmail(String email) {
		Owner owner = new Owner();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM owner WHERE email = ?;");
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();

			result.next();

			owner.setId(result.getInt("id"));
			owner.setFirstName(result.getString("first_name"));
			owner.setLastName(result.getString("last_name"));
			owner.setEmail(result.getString("email"));
			owner.setPhoneNumber(result.getString("phone_number"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return owner;
	}

	@Override
	public boolean exists(String email) {
		boolean exists = false;
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT EXISTS (SELECT email FROM owner WHERE email = ?);");
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			
			result.next();
			
			exists = result.getBoolean(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	
	@Override
	public boolean checkPassword(String email, String password) {
		boolean match = false;
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT (password = ?) AS match FROM owner where email = ?  ;");
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, email);
			ResultSet result = preparedStatement.executeQuery();
			
			result.next();
			
			match = result.getBoolean(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return match;
	}

}
