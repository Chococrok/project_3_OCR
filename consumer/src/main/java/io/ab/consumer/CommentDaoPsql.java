package io.ab.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Comment;

public class CommentDaoPsql implements CommentDao {
	
	private DaoFactory daoFactory;
	
	public CommentDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public List<Comment> findAllBy(String column, int id) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT id, content, time_stamp FROM comment where %1s = %2d;", column, id));

            while (results.next()) {

            		Comment comment = new Comment();
                comment.setId(results.getInt("id"));
                comment.setContent(results.getString("content"));
                comment.setTimestamp(results.getTimestamp("time_stamp"));
                
                comments.add(comment);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	public void addOneBy(String column, int idForColumn, String content, Timestamp timestamp) {
		try {
			Connection connection = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(String.format("INSERT INTO comment (%s, content, time_stamp) VALUES (?, ?, ?);", column));
			preparedStatement.setInt(1, idForColumn);
			preparedStatement.setString(2, content);
			preparedStatement.setTimestamp(3, timestamp);
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}