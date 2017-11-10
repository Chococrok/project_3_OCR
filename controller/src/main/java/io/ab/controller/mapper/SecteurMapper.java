package io.ab.controller.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import io.ab.model.Secteur;
import io.ab.model.Site;

public class SecteurMapper {

	private static final String SECTEUR_MAPPER = "SecteurMapper";

	public static Secteur map(HttpServletRequest request) {
		Secteur secteur = new Secteur();
		Site site = new Site();
		
		try {
			secteur.setId(Integer.parseInt(request.getParameter("secteurId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, SECTEUR_MAPPER + ": no secteurId were provided");
		}
		
		try {
			site.setId(Integer.parseInt(request.getParameter("siteId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, SECTEUR_MAPPER + ": no siteId were provided.");
		}
		
		secteur.setName(request.getParameter("name"));
		secteur.setDescription(request.getParameter("description"));
		secteur.setSite(site);
		return secteur;
	}
}
