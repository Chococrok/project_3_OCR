package io.ab.business;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.LongueurDao;
import io.ab.consumer.VoieDao;
import io.ab.model.Comment;
import io.ab.model.Entity;
import io.ab.model.Voie;

public class VoieService {
	
	private CommentDao commentDao;
	private LongueurDao longueurDao;
	private VoieDao voieDao;
	
	public VoieService(ServletContext context) {
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();
		this.longueurDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getLongueurDao();
		this.voieDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getVoieDao();
	}

	public Voie findOneWithCommentsAndLongueurs(int id) {
		Voie voie = this.voieDao.findOne(id);
		voie.setComments(this.commentDao.findAllBy(Comment.VOIE_ID, id));
		voie.setLongueurs(this.longueurDao.findAllByVoie(id));
		return voie;
	}
	
	public void addComment(int id, String content) {
		this.commentDao.addOneBy(Comment.VOIE_ID, id, content, new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.voieDao.findEntitiesByName(name);
	}
	
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.voieDao.findEntitiesByCotation(cotation);
	}
}
