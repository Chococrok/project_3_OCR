package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.LongueurService;
import io.ab.climbing.business.VoieService;
import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.controller.mapper.LongueurMapper;
import io.ab.climbing.controller.mapper.VoieMapper;
import io.ab.climbing.model.Longueur;
import io.ab.climbing.model.Secteur;
import io.ab.climbing.model.Voie;

@WebServlet("/longueur/edit")
public class LongueurEditServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private LongueurService longueurService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Longueur longueur = id == null ? null : this.longueurService.findOne(id);

		if (id == null || longueur == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}

		request.setAttribute("longueur", longueur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/longueur-edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Longueur longueur = id == null ? null : this.longueurService.findOne(id);

		if (id == null || action == null || longueur == null || action.isEmpty()) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}

		switch (action) {
		case "delete":
			this.longueurService.deleteOne(id);
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		case "updateLongueur":
			this.longueurService.updateOne(LongueurMapper.map(request));
			response.sendRedirect(this.getServletContext().getContextPath() + "/voie?id=" + longueur.getVoie().getId());
			return;
		}

		request.setAttribute("longueur", longueur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie-edit.jsp").forward(request, response);
	}

}
