package io.ab.climbing.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.consumer.CommentDao;
import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.SecteurDao;
import io.ab.climbing.consumer.SiteDao;
import io.ab.climbing.model.Comment;
import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;

import java.sql.Timestamp;

@Named
public class SiteService extends AbstractService {

	@Inject
	private SecteurService secteurService;
	@Inject
	private TopoService topoService;

	public List<Site> findAll() {
		return this.daoFactory.getSiteDao().findAll();
	}
	
	public Site findOne(int id) {
		if (!this.daoFactory.getSiteDao().exists(id)) {
			return null;
		}
		Site site = this.daoFactory.getSiteDao().findOne(id);
		return site;
	}

	public Site findOneWithCommentsAndSecteurs(int id) {
		if (!this.daoFactory.getSiteDao().exists(id)) {
			return null;
		}
		Site site = this.daoFactory.getSiteDao().findOne(id);
		site.setComments(this.daoFactory.getCommentDao().findAllBy(Comment.SITE_ID, id));
		site.setSecteurs(this.secteurService.findAllBySite(id));
		return site;
	}

	public void addComment(CommentDTO commentDTO) {
		if (commentDTO.hasNullOrEmpty()) {
			return;
		}

		this.daoFactory.getCommentDao().addOneBy(Comment.SITE_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}

	public int addEmptyOne(String name) {
		return this.daoFactory.getSiteDao().insertEmptyOne(name);
	}
	
	public void addOne(Site site) {
		this.daoFactory.getSiteDao().insertOne(site);
	}

	public List<Entity> findEntitiesByName(String name) {
		return this.daoFactory.getSiteDao().findEntitiesByName(name);
	}

	public List<Entity> findEntitiesByCotation(String cotation) {
		return this.daoFactory.getSiteDao().findEntitiesByCotation(cotation);
	}
	
	public void deleteOne(int id) {
		this.daoFactory.getCommentDao().deleteBy(Comment.SITE_ID, id);
		this.topoService.deleteSiteId(id);
		
		this.secteurService.findAllBySite(id).forEach(secteur ->{
			this.secteurService.deleteOne(secteur.getId());
		});
		this.daoFactory.getSiteDao().deleteOne(id);
	}

	public void updateOne(Site site) {
		this.daoFactory.getSiteDao().updateOne(site);
		
	}

}
