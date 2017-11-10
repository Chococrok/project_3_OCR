package io.ab.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.LongueurService;
import io.ab.business.VoieService;
import io.ab.consumer.DaoFactory;
import io.ab.controller.mapper.LongueurMapper;
import io.ab.controller.mapper.VoieMapper;
import io.ab.model.Longueur;
import io.ab.model.Secteur;
import io.ab.model.Voie;

@WebServlet("/longueur/edit")
public class LongueurEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LongueurService longueurService;

	@Override
	public void init() throws ServletException {
		this.longueurService = new LongueurService(this.getServletContext());
	}

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
