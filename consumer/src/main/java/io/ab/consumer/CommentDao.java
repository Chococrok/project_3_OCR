package io.ab.consumer;

import java.sql.Timestamp;
import java.util.List;

import io.ab.model.Comment;

public interface CommentDao {
	public List<Comment> findAllBy(String column, int id);
	public void addOneBy(String column, int idForColumn, String content, Timestamp timestamp) ;
	void deleteBy(String column, int id);
}
