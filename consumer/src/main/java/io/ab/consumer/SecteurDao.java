package io.ab.consumer;

import java.util.List;

import io.ab.model.Secteur;

public interface SecteurDao {
	public List<Secteur> findAllBySite(int id);
	public Secteur findOne(int id);
}
