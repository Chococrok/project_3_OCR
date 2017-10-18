package io.ab.consumer;

import java.util.List;

import io.ab.model.Voie;


public interface VoieDao {
	public List<Voie> findAllBySecteur(int id);

}
