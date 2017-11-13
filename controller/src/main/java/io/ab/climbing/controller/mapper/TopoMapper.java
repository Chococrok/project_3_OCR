package io.ab.climbing.controller.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;

public class TopoMapper {

	private static final String TOPO_MAPPER = "TopoMapper";

	public static Topo map(HttpServletRequest request) {
		Topo topo = new Topo();
		Site site = new Site();

		try {
			site.setId(Integer.parseInt(request.getParameter("siteId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, TOPO_MAPPER + ": no siteId were provided");
		}
		
		try {
			topo.setId(Integer.parseInt(request.getParameter("topoId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, TOPO_MAPPER + ": no topoId were provided");
		}
		
		topo.setName(request.getParameter("name"));
		topo.setSite(site);

		return topo;
	}
}
