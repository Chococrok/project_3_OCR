package io.ab.business;

import java.util.List;

import javax.servlet.ServletContext;

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
	private SecteurDao secteurDao;
	
	public SiteService(ServletContext context) {
		this.siteDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSiteDao();
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();
		this.secteurDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSecteurDao();
	}

	public List<Site> findAll() {
		return this.siteDao.findAll();
	}
	
	public Site findOneWithCommentsAndSecteurs(int id) {
		Site site = this.siteDao.findOne(id);
		site.setComments(this.commentDao.findAllBy(Comment.SITE_ID, id));
		site.setSecteurs(this.secteurDao.findAllBySite(id));
		return site;
	}
	
	public void addComment(int siteId, String content) {
		this.commentDao.addOneBy(Comment.SITE_ID, siteId, content, new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.siteDao.findEntitiesByName(name);
	}
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.siteDao.findEntitiesByCotation(cotation);
	}

}
