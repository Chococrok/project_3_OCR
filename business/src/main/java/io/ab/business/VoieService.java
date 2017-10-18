package io.ab.business;

import java.sql.Timestamp;

import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.LongueurDao;
import io.ab.consumer.VoieDao;
import io.ab.model.Comment;
import io.ab.model.Voie;

public class VoieService {
	
	private CommentDao commentDao;
	private LongueurDao longueurDao;
	private VoieDao voieDao;
	
	public VoieService() {
		this.commentDao = DaoFactory.getInstance().getCommentDao();
		this.longueurDao = DaoFactory.getInstance().getLongueurDao();
		this.voieDao = DaoFactory.getInstance().getVoieDao();
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
}
