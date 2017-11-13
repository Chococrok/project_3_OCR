package io.ab.climbing.controller.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import io.ab.climbing.model.Longueur;
import io.ab.climbing.model.Voie;

public class LongueurMapper {
	
	private static final String LONGUEUR_MAPPER = "LongueurMapper";

	public static Longueur map(HttpServletRequest request) {
		Longueur longueur = new Longueur();
		Voie voie = new Voie();
		
		try {
			longueur.setId(Integer.parseInt(request.getParameter("longueurId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, LONGUEUR_MAPPER + ": no longueurId were provided.");
		}
		
		try {
			voie.setId(Integer.parseInt(request.getParameter("voieId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, LONGUEUR_MAPPER + ": no voieId were provided");
		}
		
		longueur.setName(request.getParameter("name"));
		longueur.setVoie(voie);
		longueur.setCotation(request.getParameter("cotation"));
		
		try {
			longueur.setLength(Integer.parseInt(request.getParameter("length")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, LONGUEUR_MAPPER + ": length was not defined. Automatically set to -1");
			longueur.setLength(-1);
		}
		
		return longueur;
	}
}
