package io.ab.consumer;

import java.util.List;

import io.ab.model.Entity;
import io.ab.model.Secteur;

public interface SecteurDao {
	public List<Secteur> findAllBySite(int id);
	public Secteur findOne(int id);
	public List<Entity> findEntitiesByName(String name);
	public List<Entity> findEntitiesByCotation(String cotation);
	void deleteOne(int id);
	void deleteBySite(int id);
}
