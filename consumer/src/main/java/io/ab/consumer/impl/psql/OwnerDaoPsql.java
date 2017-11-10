package io.ab.consumer.impl.psql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.OwnerDao;
import io.ab.model.Owner;
import io.ab.model.Site;
import io.ab.model.Topo;

public class OwnerDaoPsql implements OwnerDao {

	private DaoFactory daoFactory;

	public OwnerDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Owner> findByTopo(int id) {
		List<Owner> owners = new ArrayList<Owner>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT owner.*, topo_owner.available FROM owner INNER JOIN topo_owner ON (topo_owner.topo_id = ?) WHERE topo_owner.owner_id = owner.id;");
			preparedStatement.setInt(1, id);
			results = preparedStatement.executeQuery();

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
		return owners;
	}

	@Override
	public Owner findOneById(int id) {
		Owner owner = new Owner();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM owner WHERE id = ?;");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();

			result.next();

			owner.setId(result.getInt("id"));
			owner.setFirstName(result.getString("first_name"));
			owner.setLastName(result.getString("last_name"));
			owner.setEmail(result.getString("email"));
			owner.setPhoneNumber(result.getString("phone_number"));

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
		return owner;
	}

	@Override
	public Owner findOneByEmail(String email) {
		Owner owner = new Owner();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM owner WHERE email = ?;");
			preparedStatement.setString(1, email);
			result = preparedStatement.executeQuery();

			result.next();

			owner.setId(result.getInt("id"));
			owner.setFirstName(result.getString("first_name"));
			owner.setLastName(result.getString("last_name"));
			owner.setEmail(result.getString("email"));
			owner.setPhoneNumber(result.getString("phone_number"));

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
		return owner;
	}

	@Override
	public boolean exists(String email) {
		boolean exists = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT email FROM owner WHERE email = ?);");
			preparedStatement.setString(1, email);
			result = preparedStatement.executeQuery();

			result.next();

			exists = result.getBoolean(1);
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
		return exists;
	}

	@Override
	public boolean checkPassword(String email, String password) {
		boolean match = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT (password = ?) AS match FROM owner WHERE email = ?;");
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, email);
			result = preparedStatement.executeQuery();

			result.next();

			match = result.getBoolean(1);
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
		return match;
	}

	@Override
	public void updateEmail(String email, int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE owner SET email = ?  WHERE id = ?;");
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, id);
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
	public void updatePhone(String phone, int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE owner SET phone_number = ?  WHERE id = ?;");
			preparedStatement.setString(1, phone);
			preparedStatement.setInt(2, id);
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
	public void addTopo(int topoId, int ownerId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO topo_owner (topo_id, owner_id) VALUES (?, ?);");
			preparedStatement.setInt(1, topoId);
			preparedStatement.setInt(2, ownerId);
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
	public int createOne(String firstName, String lastName, String email, String password) {
		int id = -1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO owner (first_name, last_name, email, password) VALUES (?, ?, ?, ?) RETURNING id");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
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
	
	@Override
	public void deleteOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM owner WHERE id = ?");
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
