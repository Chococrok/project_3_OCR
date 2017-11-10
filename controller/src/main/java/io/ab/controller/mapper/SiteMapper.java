package io.ab.controller.mapper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import io.ab.model.Site;

public class SiteMapper {

	private static final String SITE_MAPPER = "SiteMapper";

	public static Site map(HttpServletRequest request) {
		Site site = new Site();

		try {
			site.setId(Integer.parseInt(request.getParameter("siteId")));
		} catch (NumberFormatException n) {
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, SITE_MAPPER + ": no siteId were provided.");
		}

		site.setName(request.getParameter("name"));
		site.setDescription(request.getParameter("description"));
		site.setHowToFind(request.getParameter("howToFind"));

		try {
			site.setLatitude(new BigDecimal(request.getParameter("latitude")));
			site.setLongitude(new BigDecimal(request.getParameter("longitude")));
		} catch (NumberFormatException e) {
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, SITE_MAPPER + ": lat or long was invalid.");
			site.setLatitude(new BigDecimal(-1));
			site.setLongitude(new BigDecimal(-1));
		}
		return site;
	}
}
