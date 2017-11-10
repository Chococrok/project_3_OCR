package io.ab.controller.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import io.ab.model.Secteur;
import io.ab.model.Voie;

public class VoieMapper {

	private static final String VOIE_MAPPER = "VoieMapper";

	public static Voie map(HttpServletRequest request) {
		Voie voie = new Voie();
		Secteur secteur = new Secteur();

		try {
			secteur.setId(Integer.parseInt(request.getParameter("secteurId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, VOIE_MAPPER + ": no secteurId were provided, this is normal of updating a voie.");
		}
		
		try {
			voie.setId(Integer.parseInt(request.getParameter("voieId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, VOIE_MAPPER + ": no voieId were provided, this is normal for inserting new voie.");
		}
		
		voie.setName(request.getParameter("name"));
		voie.setSecteur(secteur);
		voie.setDescription(request.getParameter("description"));
		voie.setCotation(request.getParameter("cotation"));
		
		try {
			voie.setLength(Integer.parseInt(request.getParameter("length")));
			voie.setPointNumber(Integer.parseInt(request.getParameter("pointNumber")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, VOIE_MAPPER + ": length or point number were not defined. Automatically set to -1");
			voie.setLength(-1);
			voie.setPointNumber(-1);
		}
		return voie;
	}

}
