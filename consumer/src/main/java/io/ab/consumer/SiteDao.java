package io.ab.consumer;

import java.util.List;

import io.ab.model.Entity;
import io.ab.model.Site;

public interface SiteDao {
	public List<Site> findAll();
	public Site findOne(int id);
	public List<Entity> findEntitiesByName(String name);
	public List<Entity> findEntitiesByCotation(String cotation);
}


