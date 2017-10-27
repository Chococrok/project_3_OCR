package io.ab.business;

import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.SecteurDao;
import io.ab.consumer.VoieDao;
import io.ab.model.Comment;
import io.ab.model.Entity;
import io.ab.model.Secteur;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

public class SecteurService {
	
	private CommentDao commentDao;
	private SecteurDao secteurDao;
	private VoieDao voieDao;
	
	public SecteurService(ServletContext context) {
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();
		this.secteurDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSecteurDao();
		this.voieDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getVoieDao();
	}

	public Secteur findOneWithCommentsAndVoies(int id) {
		Secteur secteur = this.secteurDao.findOne(id);
		secteur.setComments(this.commentDao.findAllBy(Comment.SECTEUR_ID, id));
		secteur.setVoies(this.voieDao.findAllBySecteur(id));
		return secteur;
	}
	
	public void addComment(int id, String content) {
		this.commentDao.addOneBy(Comment.SECTEUR_ID, id, content, new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.secteurDao.findEntitiesByName(name);
	}
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.secteurDao.findEntitiesByCotation(cotation);
	}

}
