package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Site;

public interface SiteDao {
	public List<Site> findAll();
	public Site findOne(int id);
	public int insertEmptyOne(String name);
	public List<Entity> findEntitiesByName(String name);
	public List<Entity> findEntitiesByCotation(String cotation);
	public void deleteOne(int id);
	boolean exists(int id);
	public void updateOne(Site site);
	public void insertOne(Site site);
}


