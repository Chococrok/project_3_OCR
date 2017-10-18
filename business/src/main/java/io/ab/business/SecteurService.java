package io.ab.business;

import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.SecteurDao;
import io.ab.consumer.VoieDao;
import io.ab.model.Comment;
import io.ab.model.Secteur;
import java.sql.Timestamp;

public class SecteurService {
	
	private CommentDao commentDao;
	private SecteurDao secteurDao;
	private VoieDao voieDao;
	
	public SecteurService() {
		this.commentDao = DaoFactory.getInstance().getCommentDao();
		this.secteurDao = DaoFactory.getInstance().getSecteurDao();
		this.voieDao = DaoFactory.getInstance().getVoieDao();
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

}
