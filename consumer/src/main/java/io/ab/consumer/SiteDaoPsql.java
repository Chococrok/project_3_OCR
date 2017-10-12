package io.ab.consumer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Site;

public class SiteDaoPsql implements SiteDao {
	
	private DaoFactory daoFactory;
	
	public SiteDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Site> getAll() {
		try {
			Connection connection = this.daoFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Site>();
	}

}
