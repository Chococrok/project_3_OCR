package io.ab.climbing.business;

import javax.inject.Named;
import javax.servlet.ServletContext;

import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.LongueurDao;
import io.ab.climbing.model.Longueur;

@Named
public class LongueurService extends AbstractService {

	public Longueur findOne(int id) {
		return this.daoFactory.getLongueurDao().findOne(id);
	}
	
	public void deleteOne(int id) {
		this.daoFactory.getLongueurDao().deleteOne(id);
	}
	
	public void updateOne(Longueur longueur) {
		this.daoFactory.getLongueurDao().updateOne(longueur);
	}
}
