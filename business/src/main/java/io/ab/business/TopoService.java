package io.ab.business;

import java.util.List;

import javax.servlet.ServletContext;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.OwnerDao;
import io.ab.consumer.SiteDao;
import io.ab.consumer.TopoDao;
import io.ab.model.Topo;

public class TopoService {
	
	private TopoDao topoDao;
	private SiteDao siteDao;
	private OwnerDao ownerDao;

	public TopoService(ServletContext context) {
		this.topoDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getTopoDao();
		this.siteDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSiteDao();
		this.ownerDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getOwnerDao();
	}
	
	public List<Topo> findAll() {
		List<Topo> topos = this.topoDao.findAll();
		for(Topo topo: topos) {
			topo.setSite(this.siteDao.findOne(topo.getSite().getId()));
		}
		return topos;
	}
	
	public List<Topo> findAllByOwner(int id) {
		List<Topo> topos = this.topoDao.findAllByOwner(id);
		for(Topo topo: topos) {
			topo.setSite(this.siteDao.findOne(topo.getSite().getId()));
		}
		return topos;
	}
	
	public List<Topo> findAllByNotOwner(int id) {
		List<Topo> allTopos = this.topoDao.findAll();
		List<Topo> toposOwned = this.topoDao.findAllByOwner(id);
		
		for (Topo topoOwned : toposOwned) {
			allTopos.remove(topoOwned);
		}

		return allTopos;
	}
	
	public List<Topo> findAllBySite(int id) {
		List<Topo> topos = this.topoDao.findAllBySite(id);
		return topos;
	}
	
	public Topo findOneWithOwners(int id) {
		Topo topo = this.topoDao.findOne(id);
		topo.setOwners(this.ownerDao.findByTopo(id));
		topo.setSite(this.siteDao.findOne(topo.getSite().getId()));
		return topo;
	}
	
	public void updateAvailability(int ownerId, int topoId, boolean available) {
		this.topoDao.updateAvailability(ownerId, topoId, available);
	}
	
	public int createOne(String topoName, int siteId) {
		return this.topoDao.createOne(topoName, siteId);
	}
}
