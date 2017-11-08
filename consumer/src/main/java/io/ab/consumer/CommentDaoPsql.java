package io.ab.consumer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
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
	
	CommentDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Comment> findAllBy(String column, int id) {
		List<Comment> comments = new ArrayList<Comment>();

		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			connection = this.daoFactory.getConnection();
			statement = connection.createStatement();
            results = statement.executeQuery(String.format("SELECT id, content, time_stamp FROM comment where %1s = %2d;", column, id));

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
		} finally {
			try {
				connection.close();
				statement.close();
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return comments;
	}
	
	public void addOneBy(String column, int idForColumn, String content, Timestamp timestamp) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(String.format("INSERT INTO comment (%s, content, time_stamp) VALUES (?, ?, ?);", column));
			preparedStatement.setInt(1, idForColumn);
			preparedStatement.setString(2, content);
			preparedStatement.setTimestamp(3, timestamp);
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void deleteBy(String column, int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(String.format("DELETE FROM comment WHERE %s = ?;", column));
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
