package io.ab.climbing.controller.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import io.ab.climbing.model.Owner;

public class OwnerMapper {

	private static final String OWNER_MAPPER = "OwnerMapper";

	public static Owner map(HttpServletRequest request) {
		Owner owner = new Owner();
		
		try {
			owner.setId(Integer.parseInt(request.getParameter("ownerId")));
		} catch (NumberFormatException n){
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.log(Level.INFO, OWNER_MAPPER + ": no secteurId were provided");
		}
		
		owner.setFirstName(request.getParameter("firstName"));
		owner.setLastName(request.getParameter("lastName"));
		owner.setEmail(request.getParameter("email"));
		owner.setPhoneNumber(request.getParameter("phone"));
		return owner;
	}
}
