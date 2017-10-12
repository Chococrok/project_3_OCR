package io.ab.model;

import java.util.List;


public class Topo {

	private Integer id;

	private List<Comment> comments;

	private List<Owner> owners;

	public Topo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setTopo(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setTopo(null);

		return comment;
	}

	public List<Owner> getOwners() {
		return this.owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public void addOwner(Owner owner) {
		getOwners().add(owner);
	}

	public void removeOwner(Owner owner) {
		getOwners().remove(owner);
	}

}