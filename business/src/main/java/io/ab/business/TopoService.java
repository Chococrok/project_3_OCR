package io.ab.business;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

import io.ab.business.dto.CommentDTO;
import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.OwnerDao;
import io.ab.consumer.SiteDao;
import io.ab.consumer.TopoDao;
import io.ab.model.Comment;
import io.ab.model.Topo;

public class TopoService {

	private TopoDao topoDao;
	private SiteDao siteDao;
	private OwnerDao ownerDao;
	private CommentDao commentDao;

	private String error;

	public TopoService(ServletContext context) {
		this.topoDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getTopoDao();
		this.siteDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSiteDao();
		this.ownerDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getOwnerDao();
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();

	}

	public List<Topo> findAll() {
		List<Topo> topos = this.topoDao.findAll();
		for (Topo topo : topos) {
			int siteId = topo.getSite().getId();

			if (siteId != 0) {
				topo.setSite(this.siteDao.findOne(siteId));
			}
		}
		return topos;
	}

	public List<Topo> findAllByOwner(int id) {
		List<Topo> topos = this.topoDao.findAllByOwner(id);
		for (Topo topo : topos) {
			int siteId = topo.getSite().getId();

			if (siteId != 0) {
				topo.setSite(this.siteDao.findOne(siteId));
			}
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
		
		int siteId = topo.getSite().getId();
		if (siteId != 0) {
			topo.setSite(this.siteDao.findOne(siteId));
		}
		
		topo.setComments(this.commentDao.findAllBy(Comment.TOPO_ID, id));
		return topo;
	}

	public void updateAvailability(int ownerId, int topoId, boolean available) {
		this.topoDao.updateAvailability(ownerId, topoId, available);
	}
	
	public void deleteSiteId(int id) {
		this.topoDao.deleteSiteId(id);
	}

	public int createOne(String topoName, int siteId) {
		return this.topoDao.createOne(topoName, siteId);
	}

	public void addComment(CommentDTO commentDTO) {
		error = null;
		if (commentDTO.hasNullOrEmpty()) {
			error = "Le commentaire est vide";
			return;
		}
		this.commentDao.addOneBy(Comment.TOPO_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}

	public boolean hasError() {
		return this.error != null;
	}

	public String getError() {
		return error;
	}
}
