package io.ab.consumer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Comment;

public class CommentDaoPsql implements CommentDao {
	
	private final String TOPO_ID = "topo_id";
	private final String SITE_ID = "site_id";
	private final String VOIE_ID = "voie_id";
	private final String SECTEUR_ID = "secteur_id";
	private final String LONGUEURE_ID = "longueure_id";
	
	private DaoFactory daoFactory;
	
	public CommentDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Comment> findAllBySite(int id) {
		return selectColumn(this.SITE_ID, id);
	}

	public List<Comment> findAllByTopo(int id) {
		// TODO Auto-generated method stub
		return selectColumn(this.TOPO_ID, id);
	}

	public List<Comment> findAllBySecteur(int id) {
		// TODO Auto-generated method stub
		return selectColumn(this.SECTEUR_ID, id);
	}

	public List<Comment> findAllByVoie(int id) {
		// TODO Auto-generated method stub
		return selectColumn(this.VOIE_ID, id);
	}

	public List<Comment> findAllByLongueure(int id) {
		// TODO Auto-generated method stub
		return selectColumn(this.LONGUEURE_ID, id);
	}
	
	private List<Comment> selectColumn(String column, int id) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT id, content FROM comment where %1s = %2d;", column, id));

            while (results.next()) {

            		Comment comment = new Comment();
                comment.setId(results.getInt("id"));
                comment.setContent(results.getString("content"));
                
                comments.add(comment);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
}
