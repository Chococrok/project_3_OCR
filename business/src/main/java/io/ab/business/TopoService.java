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
	
	public Topo findOneWithOwners(int id) {
		Topo topo = this.topoDao.findOne(id);
		topo.setOwners(this.ownerDao.findByTopo(id));
		topo.setSite(this.siteDao.findOne(topo.getSite().getId()));
		return topo;
	}
}
