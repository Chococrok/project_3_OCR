package io.ab.climbing.consumer.impl.psql;

import javax.inject.Inject;

import io.ab.climbing.consumer.DaoFactory;

public abstract class AbstractDaoPsql {

	@Inject
	DaoFactory daoFactory;

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}
