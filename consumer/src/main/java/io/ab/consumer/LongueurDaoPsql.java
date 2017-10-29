package io.ab.consumer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import io.ab.model.Longueur;

public class LongueurDaoPsql implements LongueurDao {
	
	private DaoFactory daoFactory;
	
	LongueurDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public List<Longueur> findAllByVoie(int id) {
		List<Longueur> longueurs = new ArrayList<Longueur>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT id, name, length, cotation FROM longueur WHERE voie_id = %d;", id));

            while (results.next()) {

            		Longueur longueur = new Longueur();
            		longueur.setId(results.getInt("id"));
            		longueur.setName(results.getString("name"));
            		longueur.setLength(results.getInt("length"));
            		longueur.setCotation(results.getString("cotation"));
 
            		longueurs.add(longueur);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return longueurs;
	}
}
