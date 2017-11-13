package io.ab.climbing.business;

import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.model.Comment;
import io.ab.climbing.model.Entity;
import io.ab.climbing.model.Secteur;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SecteurService extends AbstractService {
	
	@Inject
	private VoieService voieService;
	
	public Secteur findOne(int id) {
		Secteur secteur = this.daoFactory.getSecteurDao().findOne(id);
		return secteur;
	}

	public Secteur findOneWithCommentsAndVoies(int id) {
		Secteur secteur = this.daoFactory.getSecteurDao().findOne(id);
		secteur.setComments(this.daoFactory.getCommentDao().findAllBy(Comment.SECTEUR_ID, id));
		secteur.setVoies(this.voieService.findAllBySecteur(id));
		return secteur;
	}
	
	public List<Secteur> findAllBySite(int id) {
		return this.daoFactory.getSecteurDao().findAllBySite(id);
	}
	
	public void addComment(CommentDTO commentDTO) {
		if (commentDTO.hasNullOrEmpty()) {
			return;
		}

		this.daoFactory.getCommentDao().addOneBy(Comment.SECTEUR_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.daoFactory.getSecteurDao().findEntitiesByName(name);
	}
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.daoFactory.getSecteurDao().findEntitiesByCotation(cotation);
	}
	
	public void deleteOne(int id) {
		this.daoFactory.getCommentDao().deleteBy(Comment.SECTEUR_ID, id);

		this.voieService.findAllBySecteur(id).forEach( voie -> {
			this.voieService.deleteOne(voie.getId());
		});
		this.daoFactory.getSecteurDao().deleteOne(id);
	}
	
	public void updateOne(Secteur secteur) {
		this.daoFactory.getSecteurDao().updateOne(secteur);
	}

	public void addOne(Secteur secteur) {
		this.daoFactory.getSecteurDao().insertOne(secteur);
		
	}

}
