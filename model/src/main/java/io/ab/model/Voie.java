package io.ab.model;

import java.util.List;


public class Voie {

	private Integer id;

	private String cotation;

	private String description;

	private Integer length;

	private String name;

	private Integer pointNumber;

	private List<Comment> comments;

	private List<Longueur> longueurs;

	private Secteur secteur;

	public Voie() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCotation() {
		return this.cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPointNumber() {
		return this.pointNumber;
	}

	public void setPointNumber(Integer pointNumber) {
		this.pointNumber = pointNumber;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setVoie(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setVoie(null);

		return comment;
	}

	public List<Longueur> getLongueurs() {
		return this.longueurs;
	}

	public void setLongueurs(List<Longueur> longueurs) {
		this.longueurs = longueurs;
	}

	public Longueur addLongueur(Longueur longueur) {
		getLongueurs().add(longueur);
		longueur.setVoie(this);

		return longueur;
	}

	public Longueur removeLongueur(Longueur longueur) {
		getLongueurs().remove(longueur);
		longueur.setVoie(null);

		return longueur;
	}

	public Secteur getSecteur() {
		return this.secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

}