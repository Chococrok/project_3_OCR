package io.ab.consumer;

import java.util.List;

import io.ab.model.Site;

public interface SiteDao {
	public List<Site> findAll();
}
