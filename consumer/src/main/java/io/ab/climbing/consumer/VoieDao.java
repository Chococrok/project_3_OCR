package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Voie;


public interface VoieDao {
	public List<Voie> findAllBySecteur(int id);
	public Voie findOne(int id);
	public List<Entity> findEntitiesByName(String name);
	public List<Entity> findEntitiesByCotation(String cotation);
	void deleteOne(int id);
	void deleteBySecteur(int id);
	void updateOne(Voie voie);
	public void insertOne(Voie voie);
}
