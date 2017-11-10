package io.ab.controller.mapper;

import javax.servlet.http.HttpServletRequest;

import io.ab.model.Voie;

public class VoieMapper {

	public static Voie map(HttpServletRequest request) {
		String paramId = request.getParameter("id");
		if (paramId == null || paramId.isEmpty()) {
			return null;
		}
		Voie voie = new Voie();
		voie.setId(Integer.parseInt(paramId));
		voie.setName(request.getParameter("name"));
		voie.setDescription(request.getParameter("description"));
		voie.setCotation(request.getParameter("cotation"));
		voie.setLength(request.getParameter("length") == null ? -1 : Integer.parseInt(request.getParameter("length")));
		voie.setPointNumber(request.getParameter("pointNumber") == null ? -1 : Integer.parseInt(request.getParameter("pointNumber")));
		return voie;
	}

}
