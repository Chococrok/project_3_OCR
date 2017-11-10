package io.ab.business;

import io.ab.business.dto.CommentDTO;
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
	private VoieService voieService;
	
	public SecteurService(ServletContext context) {
		this.commentDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getCommentDao();
		this.secteurDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getSecteurDao();
		this.voieService = new VoieService(context);
	}
	
	public Secteur findOne(int id) {
		Secteur secteur = this.secteurDao.findOne(id);
		return secteur;
	}

	public Secteur findOneWithCommentsAndVoies(int id) {
		Secteur secteur = this.secteurDao.findOne(id);
		secteur.setComments(this.commentDao.findAllBy(Comment.SECTEUR_ID, id));
		secteur.setVoies(this.voieService.findAllBySecteur(id));
		return secteur;
	}
	
	public List<Secteur> findAllBySite(int id) {
		return this.secteurDao.findAllBySite(id);
	}
	
	public void addComment(CommentDTO commentDTO) {
		if (commentDTO.hasNullOrEmpty()) {
			return;
		}

		this.commentDao.addOneBy(Comment.SECTEUR_ID, commentDTO.getEntityId(), commentDTO.getContent(),
				new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Entity> findEntitiesByName(String name){
		return this.secteurDao.findEntitiesByName(name);
	}
	public List<Entity> findEntitiesByCotation(String cotation){
		return this.secteurDao.findEntitiesByCotation(cotation);
	}
	
	public void deleteOne(int id) {
		this.commentDao.deleteBy(Comment.SECTEUR_ID, id);

		this.voieService.findAllBySecteur(id).forEach( voie -> {
			this.voieService.deleteOne(voie.getId());
		});
		this.secteurDao.deleteOne(id);
	}
	
	public void updateOne(Secteur secteur) {
		this.secteurDao.updateOne(secteur);
	}

	public void addOne(Secteur secteur) {
		this.secteurDao.insertOne(secteur);
		
	}

}
