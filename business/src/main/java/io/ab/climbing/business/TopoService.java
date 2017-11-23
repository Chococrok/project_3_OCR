package io.ab.climbing.business;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Named;

import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.model.Comment;
import io.ab.climbing.model.Topo;

@Named
public class TopoService extends AbstractService {

	private String error;

	public List<Topo> findAll() {
		List<Topo> topos = this.daoFactory.getTopoDao().findAll();
		for (Topo topo : topos) {
			int siteId = topo.getSite().getId();

			if (siteId != 0) {
				topo.setSite(this.daoFactory.getSiteDao().findOne(siteId));
			}
		}
		return topos;
	}

	public List<Topo> findAllByOwner(int id) {
		List<Topo> topos = this.daoFactory.getTopoDao().findAllByOwner(id);
		for (Topo topo : topos) {
			int siteId = topo.getSite().getId();

			if (siteId != 0) {
				topo.setSite(this.daoFactory.getSiteDao().findOne(siteId));
			}
		}
		return topos;
	}

	public List<Topo> findAllByNotOwner(int id) {
		List<Topo> allTopos = this.daoFactory.getTopoDao().findAll();
		List<Topo> toposOwned = this.daoFactory.getTopoDao().findAllByOwner(id);

		for (Topo topoOwned : toposOwned) {
			allTopos.remove(topoOwned);
		}

		return allTopos;
	}

	public List<Topo> findAllBySite(int id) {
		List<Topo> topos = this.daoFactory.getTopoDao().findAllBySite(id);
		return topos;
	}

	public Topo findOneWithOwners(int id) {
		Topo topo = this.daoFactory.getTopoDao().findOne(id);
		topo.setOwners(this.daoFactory.getOwnerDao().findByTopo(id));
		
		int siteId = topo.getSite().getId();
		if (siteId != 0) {
			topo.setSite(this.daoFactory.getSiteDao().findOne(siteId));
		}
		
		topo.setComments(this.daoFactory.getCommentDao().findAllBy(Comment.TOPO_ID, id));
		return topo;
	}

	public void updateAvailability(int ownerId, int topoId, boolean available) {
		this.daoFactory.getTopoDao().updateAvailability(ownerId, topoId, available);
	}
	
	public void deleteSiteId(int siteId) {
		this.daoFactory.getTopoDao().deleteSiteId(siteId);
	}
	
	public void deleteSiteIdByTopo(int siteId, int topoId) {
		this.daoFactory.getTopoDao().deleteSiteIdByTopo(siteId, topoId);
	}

	public int createOne(String topoName, int siteId) {
		return this.daoFactory.getTopoDao().createOne(topoName, siteId);
	}

	public void addComment(CommentDTO commentDTO) {
		error = null;
		if (commentDTO.hasNullOrEmpty()) {
			error = "Le commentaire est vide";
			return;
		}
		this.daoFactory.getCommentDao().addOneBy(Comment.TOPO_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}

	public boolean hasError() {
		return this.error != null;
	}

	public String getError() {
		return error;
	}

	public void updateSite(Integer topoId, Integer siteId) {
		this.daoFactory.getTopoDao().updateSite(topoId, siteId);
		
	}

	public void updateOne(Topo topo) {
		this.daoFactory.getTopoDao().updateOne(topo);
		
	}

	public void deleteOne(int id) {
		this.daoFactory.getTopoDao().deleteLinkToOwner(id);
		this.daoFactory.getTopoDao().deleteOne(id);
		
	}
}
