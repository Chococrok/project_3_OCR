package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Secteur;

public interface SecteurDao {
	public List<Secteur> findAllBySite(int id);
	public Secteur findOne(int id);
	public List<Entity> findEntitiesByName(String name);
	public List<Entity> findEntitiesByCotation(String cotation);
	void deleteOne(int id);
	void deleteBySite(int id);
	public void updateOne(Secteur secteur);
	public void insertOne(Secteur secteur);
}
