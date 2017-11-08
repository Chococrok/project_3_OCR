package io.ab.consumer;

import java.util.List;

import io.ab.model.Entity;
import io.ab.model.Voie;


public interface VoieDao {
	public List<Voie> findAllBySecteur(int id);
	public Voie findOne(int id);
	public List<Entity> findEntitiesByName(String name);
	public List<Entity> findEntitiesByCotation(String cotation);
	void deleteOne(int id);
	void deleteBySecteur(int id);
}
