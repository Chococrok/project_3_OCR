package io.ab.climbing.model;

import java.util.List;


public class Topo extends Entity {
	
	private Site site;

	private List<Comment> comments;

	private List<Owner> owners;
	
	private Boolean available;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getId() == ((Topo) obj).getId();
	}
	
	

}