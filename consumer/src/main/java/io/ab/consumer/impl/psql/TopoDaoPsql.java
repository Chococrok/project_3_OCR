package io.ab.consumer.impl.psql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.TopoDao;
import io.ab.model.Site;
import io.ab.model.Topo;

public class TopoDaoPsql implements TopoDao {

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String SITE_ID = "site_id";
	public static final String AVAILABLE = "available";

	private DaoFactory daoFactory;

	public TopoDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Topo> findAll() {
		List<Topo> topos = new ArrayList<Topo>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM topo;");
			results = preparedStatement.executeQuery();

			while (results.next()) {

				Topo topo = new Topo();

				topo.setName(results.getString(NAME));
				topo.setId(results.getInt(ID));
				Site site = new Site();
				site.setId(results.getInt(SITE_ID));
				topo.setSite(site);

				topos.add(topo);
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
				e.printStackTrace();
			}
		}
		return topos;
	}

	@Override
	public void addOne(int siteId, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Topo findOne(int id) {
		Topo topo = new Topo();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM topo WHERE id = ?;");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();

			result.next();

			topo.setName(result.getString(NAME));
			topo.setId(result.getInt(ID));
			Site site = new Site();
			site.setId(result.getInt(SITE_ID));
			topo.setSite(site);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return topo;
	}

	@Override
	public List<Topo> findAllByOwner(int id) {
		List<Topo> topos = new ArrayList<Topo>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT topo.*, topo_owner.available FROM topo INNER JOIN topo_owner ON (topo_owner.owner_id = ?) WHERE topo.id =  topo_owner.topo_id;");
			preparedStatement.setInt(1, id);
			results = preparedStatement.executeQuery();
			
			while (results.next()) {

				Topo topo = new Topo();

				topo.setName(results.getString(NAME));
				topo.setId(results.getInt(ID));
				Site site = new Site();
				site.setId(results.getInt(SITE_ID));
				topo.setSite(site);
				topo.setAvailable(results.getBoolean(AVAILABLE));

				topos.add(topo);
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
				e.printStackTrace();
			}
		}
		return topos;
	}
	
	@Override
	public List<Topo> findAllBySite(int id) {
		List<Topo> topos = new ArrayList<Topo>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM topo WHERE site_id =  ?;");
			preparedStatement.setInt(1, id);
			results = preparedStatement.executeQuery();
			
			while (results.next()) {

				Topo topo = new Topo();

				topo.setName(results.getString(NAME));
				topo.setId(results.getInt(ID));
				Site site = new Site();
				site.setId(results.getInt(SITE_ID));
				topo.setSite(site);

				topos.add(topo);
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
				e.printStackTrace();
			}
		}
		return topos;
	}
	
	//TODO correct sql statement !
	/*@Override
	public List<Topo> findAllByNotOwner(int id) {
		List<Topo> topos = new ArrayList<Topo>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"???????");
			preparedStatement.setInt(1, id);
			results = preparedStatement.executeQuery();
			
			while (results.next()) {

				Topo topo = new Topo();

				topo.setName(results.getString(NAME));
				topo.setId(results.getInt(ID));
				Site site = new Site();
				site.setId(results.getInt(SITE_ID));
				topo.setSite(site);
				topo.setAvailable(results.getBoolean(AVAILABLE));

				topos.add(topo);
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
				e.printStackTrace();
			}
		}
		return topos;
	}*/
	
	@Override
	public void updateAvailability(int ownerId, int topoId, boolean available) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(
					"UPDATE topo_owner SET available = ? WHERE topo_id = ? AND owner_id = ?;");
			preparedStatement.setBoolean(1, available);
			preparedStatement.setInt(2, topoId);
			preparedStatement.setInt(3, ownerId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int createOne(String topoName, int siteId) {
		int id = -1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO topo (name, site_id) VALUES (?, ?) RETURNING id");
			preparedStatement.setString(1, topoName);
			preparedStatement.setInt(2, siteId);
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
	public boolean areLinked(int topoId, int siteId) {
		boolean linked = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT EXISTS(SELECT id FROM topo WHERE id = ? AND site_id = ?);");
			preparedStatement.setInt(1, topoId);
			preparedStatement.setInt(2, siteId);
			results = preparedStatement.executeQuery();

			results.next();

			linked = results.getBoolean(1);

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
		return linked;
	}
	
	@Override
	public void deleteOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM topo WHERE id = ?");
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
	public void deleteSiteId(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE topo SET site_id = null WHERE site_id = ?");
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
