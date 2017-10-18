package io.ab.consumer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.ab.model.Secteur;
import io.ab.model.Voie;

public class VoieDaoPsql implements VoieDao {
	
	private DaoFactory daoFactory;
	
	public VoieDaoPsql(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	public List<Voie> findAllBySecteur(int id) {
		List<Voie> voies = new ArrayList<Voie>();
		try {
			Connection connection = this.daoFactory.getConnection();
			Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT id, name, length, point_number, cotation, description FROM voie WHERE secteur_id = %d;", id));

            while (results.next()) {

            		Voie voie = new Voie();
            		voie.setId(results.getInt("id"));
            		voie.setName(results.getString("name"));
            		voie.setDescription(results.getString("description"));
            		voie.setLength(results.getInt("length"));
            		voie.setPointNumber(results.getInt("point_number"));
            		voie.setCotation(results.getString("cotation"));
 
	            voies.add(voie);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voies;
	}
}
