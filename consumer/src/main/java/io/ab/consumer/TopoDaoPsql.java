package io.ab.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Site;
import io.ab.model.Topo;

public class TopoDaoPsql implements TopoDao {

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String SITE_ID = "site_id";
	public static final String AVAILABLE = "available";

	private DaoFactory daoFactory;

	TopoDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Topo> findAll() {
		List<Topo> topos = new ArrayList<Topo>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM topo;");

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
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM topo WHERE id = ?;");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			result.next();

			topo.setName(result.getString(NAME));
			topo.setId(result.getInt(ID));
			Site site = new Site();
			site.setId(result.getInt(SITE_ID));
			topo.setSite(site);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topo;
	}

	@Override
	public List<Topo> findAllByOwner(int id) {
		List<Topo> topos = new ArrayList<Topo>();
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT topo.*, topo_owner.available FROM topo INNER JOIN topo_owner ON (topo_owner.owner_id = 1) WHERE topo.id =  topo_owner.topo_id;");
			ResultSet results = preparedStatement.executeQuery();
			
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
		}
		return topos;
	}
}
