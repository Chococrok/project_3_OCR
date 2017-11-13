package io.ab.climbing.business;

import javax.inject.Inject;

import io.ab.climbing.consumer.DaoFactory;

public abstract class AbstractService {
	
	@Inject
	DaoFactory daoFactory;

}
