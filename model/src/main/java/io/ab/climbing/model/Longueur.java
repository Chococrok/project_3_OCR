package io.ab.climbing.model;

import java.util.List;

public class Longueur extends Entity {

	private Integer length;
	
	private String cotation;

	private List<Comment> comments;

	private Voie voie;

	public Longueur() {
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setLongueur(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setLongueur(null);

		return comment;
	}

	public Voie getVoie() {
		return this.voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

}