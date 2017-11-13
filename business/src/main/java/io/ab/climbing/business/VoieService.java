package io.ab.climbing.business;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Named;
import javax.servlet.ServletContext;

import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.consumer.CommentDao;
import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.LongueurDao;
import io.ab.climbing.consumer.VoieDao;
import io.ab.climbing.model.Comment;
import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Longueur;
import io.ab.climbing.model.Voie;

@Named
public class VoieService extends AbstractService {
	
	private String error;
	
	public Voie findOne(int id) {
		Voie voie = this.daoFactory.getVoieDao().findOne(id);
		return voie;
	}

	public Voie findOneWithCommentsAndLongueurs(int id) {
		Voie voie = this.daoFactory.getVoieDao().findOne(id);
		voie.setComments(this.daoFactory.getCommentDao().findAllBy(Comment.VOIE_ID, id));
		voie.setLongueurs(this.daoFactory.getLongueurDao().findAllByVoie(id));
		return voie;
	}
	
	public List<Voie> findAllBySecteur(int id) {
		return this.daoFactory.getVoieDao().findAllBySecteur(id);
	}
	
	public void addComment(CommentDTO commentDTO) {
		if (commentDTO.hasNullOrEmpty()) {
			return;
		}

		this.daoFactory.getCommentDao().addOneBy(Comment.VOIE_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.daoFactory.getVoieDao().findEntitiesByName(name);
	}
	
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.daoFactory.getVoieDao().findEntitiesByCotation(cotation);
	}
	
	public void deleteOne(int id) {
		this.daoFactory.getCommentDao().deleteBy(Comment.VOIE_ID, id);

		this.daoFactory.getLongueurDao().deleteByVoie(id);
		this.daoFactory.getVoieDao().deleteOne(id);
	}
	
	public void updateOne(Voie voie) {
		if(voie == null) {
			this.error = "voie non trouvée";
			return;
		}
		this.daoFactory.getVoieDao().updateOne(voie);
	}

	public void deleteLongueur(Integer id) {
		this.daoFactory.getLongueurDao().deleteOne(id);
		
	}
	
	public boolean hasError() {
		return this.error != null;
	}

	public String getError() {
		return this.error;
	}
	
	public void addOne(Voie voie) {
		this.daoFactory.getVoieDao().insertOne(voie);
	}
	
	public void addLongueur(Longueur longueur) {
		this.daoFactory.getLongueurDao().insertOne(longueur);
	}
}
