package io.ab.climbing.consumer.impl.psql;

import javax.inject.Inject;
import javax.sql.DataSource;


public abstract class AbstractDaoPsql {

	@Inject
	DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
