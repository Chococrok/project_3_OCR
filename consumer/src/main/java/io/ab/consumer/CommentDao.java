package io.ab.consumer;

import java.util.List;

import io.ab.model.Comment;

public interface CommentDao {
	public List<Comment> findAllBySite(int id);
	public List<Comment> findAllByTopo(int id);
	public List<Comment> findAllBySecteur(int id);
	public List<Comment> findAllByVoie(int id);
	public List<Comment> findAllByLongueure(int id);
}
