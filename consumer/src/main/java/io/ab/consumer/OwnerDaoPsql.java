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
				owner.setPassword(results.getString("password"));
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

}
