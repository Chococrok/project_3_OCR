package io.ab.climbing.model;

import java.util.List;


public class Secteur extends Entity {

	private String description;

	private List<Comment> comments;

	private Site site;

	private List<Voie> voies;

	public Secteur() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setSecteur(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setSecteur(null);

		return comment;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Voie> getVoies() {
		return this.voies;
	}

	public void setVoies(List<Voie> voies) {
		this.voies = voies;
	}

	public Voie addVoie(Voie voie) {
		getVoies().add(voie);
		voie.setSecteur(this);

		return voie;
	}

	public Voie removeVoie(Voie voie) {
		getVoies().remove(voie);
		voie.setSecteur(null);

		return voie;
	}

}