package io.ab.model;

import java.util.List;


public class Secteur {

	private Integer id;

	private String description;

	private String name;

	private List<Comment> comments;

	private Site site;

	private List<Voie> voies;

	public Secteur() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Voie addVoy(Voie voy) {
		getVoies().add(voy);
		voy.setSecteur(this);

		return voy;
	}

	public Voie removeVoy(Voie voy) {
		getVoies().remove(voy);
		voy.setSecteur(null);

		return voy;
	}

}