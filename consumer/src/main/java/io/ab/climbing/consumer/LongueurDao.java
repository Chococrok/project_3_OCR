package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.model.Longueur;

public interface LongueurDao {
	public List<Longueur> findAllByVoie(int id);
	void deleteOne(int id);
	void deleteByVoie(int id);
	public void insertOne(Longueur longueur);
	public Longueur findOne(int id);
	void updateOne(Longueur longueur);
}
