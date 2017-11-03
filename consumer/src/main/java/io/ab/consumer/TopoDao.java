package io.ab.consumer;

import java.util.List;

import io.ab.model.Topo;

public interface TopoDao {
	public List<Topo> findAll();
	public Topo findOne(int id);
	public void addOne(int siteId, String name);
	public List<Topo> findAllByOwner(int id);
	public List<Topo> findAllBySite(int id);
	//public List<Topo> findAllByNotOwner(int id);
	public void updateAvailability(int ownerId, int topoId, boolean available);
	public int createOne(String topoName, int siteId);
}
