package io.ab.business;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

import io.ab.business.dto.CommentDTO;
import io.ab.consumer.CommentDao;
import io.ab.consumer.DaoFactory;
import io.ab.consumer.LongueurDao;
import io.ab.consumer.VoieDao;
import io.ab.model.Comment;
import io.ab.model.Entity;
import io.ab.model.Longueur;
import io.ab.model.Voie;

public class VoieService {
	
	private CommentDao commentDao;
	private LongueurDao longueurDao;
	private VoieDao voieDao;
	private String error;
	
	public VoieService(ServletContext context) {
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();
		this.longueurDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getLongueurDao();
		this.voieDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getVoieDao();
	}
	
	public Voie findOne(int id) {
		Voie voie = this.voieDao.findOne(id);
		return voie;
	}

	public Voie findOneWithCommentsAndLongueurs(int id) {
		Voie voie = this.voieDao.findOne(id);
		voie.setComments(this.commentDao.findAllBy(Comment.VOIE_ID, id));
		voie.setLongueurs(this.longueurDao.findAllByVoie(id));
		return voie;
	}
	
	public List<Voie> findAllBySecteur(int id) {
		return this.voieDao.findAllBySecteur(id);
	}
	
	public void addComment(CommentDTO commentDTO) {
		if (commentDTO.hasNullOrEmpty()) {
			return;
		}

		this.commentDao.addOneBy(Comment.VOIE_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.voieDao.findEntitiesByName(name);
	}
	
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.voieDao.findEntitiesByCotation(cotation);
	}
	
	public void deleteOne(int id) {
		this.commentDao.deleteBy(Comment.VOIE_ID, id);

		this.longueurDao.deleteByVoie(id);
		this.voieDao.deleteOne(id);
	}
	
	public void updateOne(Voie voie) {
		if(voie == null) {
			this.error = "voie non trouvée";
			return;
		}
		this.voieDao.updateOne(voie);
	}

	public void deleteLongueur(Integer id) {
		this.longueurDao.deleteOne(id);
		
	}
	
	public boolean hasError() {
		return this.error != null;
	}

	public String getError() {
		return this.error;
	}
	
	public void addOne(Voie voie) {
		this.voieDao.insertOne(voie);
	}
	
	public void addLongueur(Longueur longueur) {
		this.longueurDao.insertOne(longueur);
	}
}
