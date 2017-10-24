package io.ab.model;

import java.math.BigDecimal;
import java.util.List;


public class Site extends Entity {

	private String description;

	private String howToFind;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private List<Comment> comments;

	private List<Secteur> secteurs;

	public Site() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHowToFind() {
		return this.howToFind;
	}

	public void setHowToFind(String howToFind) {
		this.howToFind = howToFind;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setSite(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setSite(null);

		return comment;
	}

	public List<Secteur> getSecteurs() {
		return this.secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public Secteur addSecteur(Secteur secteur) {
		getSecteurs().add(secteur);
		secteur.setSite(this);

		return secteur;
	}

	public Secteur removeSecteur(Secteur secteur) {
		getSecteurs().remove(secteur);
		secteur.setSite(null);

		return secteur;
	}

}