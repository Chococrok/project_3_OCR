package io.ab.consumer;

import java.util.List;

import io.ab.model.Topo;

public interface TopoDao {
	public List<Topo> findAll();
	public void addOne(int siteId, String name);
}
