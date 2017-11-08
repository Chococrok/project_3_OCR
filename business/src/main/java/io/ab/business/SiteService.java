package io.ab.business;

import java.util.List;

import javax.servlet.ServletContext;

import io.ab.business.dto.CommentDTO;
import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.SecteurDao;
import io.ab.consumer.SiteDao;
import io.ab.model.Comment;
import io.ab.model.Entity;
import io.ab.model.Site;
import java.sql.Timestamp;

public class SiteService {

	private SiteDao siteDao;
	private CommentDao commentDao;
	private SecteurService secteurService;
	private TopoService topoService;

	public SiteService(ServletContext context) {
		this.siteDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSiteDao();
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();
		this.secteurService = new SecteurService(context);
		this.topoService = new TopoService(context);
	}

	public List<Site> findAll() {
		return this.siteDao.findAll();
	}

	public Site findOneWithCommentsAndSecteurs(int id) {
		if (!this.siteDao.exists(id)) {
			return null;
		}
		Site site = this.siteDao.findOne(id);
		site.setComments(this.commentDao.findAllBy(Comment.SITE_ID, id));
		site.setSecteurs(this.secteurService.findAllBySite(id));
		return site;
	}

	public void addComment(CommentDTO commentDTO) {
		if (commentDTO.hasNullOrEmpty()) {
			return;
		}

		this.commentDao.addOneBy(Comment.SITE_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}

	public int createOne(String name) {
		return this.siteDao.createOne(name);
	}

	public List<Entity> findEntitiesByName(String name) {
		return this.siteDao.findEntitiesByName(name);
	}

	public List<Entity> findEntitiesByCotation(String cotation) {
		return this.siteDao.findEntitiesByCotation(cotation);
	}
	
	public void deleteOne(int id) {
		this.commentDao.deleteBy(Comment.SITE_ID, id);
		this.topoService.deleteSiteId(id);
		
		this.secteurService.findAllBySite(id).forEach(secteur ->{
			this.secteurService.deleteOne(secteur.getId());
		});
		this.siteDao.deleteOne(id);
	}

}
