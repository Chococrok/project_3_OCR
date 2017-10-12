package io.ab.model;

import java.util.List;

public class Longueur {

	private Integer id;

	private String lenght;

	private String name;

	private List<Comment> comments;

	private Voie voie;

	public Longueur() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLenght() {
		return this.lenght;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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