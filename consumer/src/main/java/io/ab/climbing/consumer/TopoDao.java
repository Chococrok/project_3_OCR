package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.model.Topo;

public interface TopoDao {
	public List<Topo> findAll();
	public Topo findOne(int id);
	public void addOne(int siteId, String name);
	public List<Topo> findAllByOwner(int id);
	public List<Topo> findAllBySite(int id);
	//public List<Topo> findAllByNotOwner(int id);
	public void updateAvailability(int ownerId, int topoId, boolean available);
	public int createOne(String topoName, int siteId);
	boolean areLinked(int topoId, int siteId);
	void deleteOne(int id);
	void deleteSiteId(int siteId);
	public void updateSite(Integer topoId, Integer siteId);
	public void deleteSiteIdByTopo(int siteId, int topoId);
	public void updateOne(Topo topo);
	void deleteLinkToOwner(Integer id);
}
