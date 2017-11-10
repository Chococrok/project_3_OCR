package io.ab.business;

import javax.servlet.ServletContext;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.LongueurDao;
import io.ab.model.Longueur;

public class LongueurService {

	private LongueurDao longueurDao;
	
	public LongueurService(ServletContext context) {
		this.longueurDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getLongueurDao();
	}

	public Longueur findOne(int id) {
		return this.longueurDao.findOne(id);
	}
	
	public void deleteOne(int id) {
		this.longueurDao.deleteOne(id);
	}
	
	public void updateOne(Longueur longueur) {
		this.longueurDao.updateOne(longueur);
	}
}
