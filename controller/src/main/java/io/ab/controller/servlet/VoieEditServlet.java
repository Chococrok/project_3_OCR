package io.ab.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SecteurService;
import io.ab.business.SiteService;
import io.ab.business.TopoService;
import io.ab.business.VoieService;
import io.ab.controller.mapper.LongueurMapper;
import io.ab.controller.mapper.VoieMapper;
import io.ab.model.Secteur;
import io.ab.model.Site;
import io.ab.model.Voie;

@WebServlet("/voie/edit")
public class VoieEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VoieService voieService;
	private TopoService topoService;

	@Override
	public void init() throws ServletException {
		this.voieService = new VoieService(this.getServletContext());
		this.topoService = new TopoService(this.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Voie voie = id == null ? null : this.voieService.findOneWithCommentsAndLongueurs(id);

		if (id == null || voie == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}

		request.setAttribute("voie", voie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie-edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Voie voie = id == null ? null : this.voieService.findOneWithCommentsAndLongueurs(id);

		if (id == null || action == null || voie == null || action.isEmpty()) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}

		switch (action) {
		case "delete":
			this.voieService.deleteOne(id);
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		case "deleteLongueur":
			String paramLongueurId = request.getParameter("longueurId");
			Integer longueurId = paramLongueurId == null || paramLongueurId.isEmpty() ? null
					: Integer.parseInt(paramLongueurId);
			if (longueurId == null) {
				break;
			}
			this.voieService.deleteLongueur(longueurId);
			voie = this.voieService.findOneWithCommentsAndLongueurs(id);
			break;
		case "updateVoie":
			this.voieService.updateOne(VoieMapper.map(request));
			response.sendRedirect(this.getServletContext().getContextPath() + "/voie?id=" + id);
			return;
		case "addLongueur":
			this.voieService.addLongueur(LongueurMapper.map(request));
			response.sendRedirect(this.getServletContext().getContextPath() + "/voie?id=" + id);
			return;
		}

		request.setAttribute("voie", voie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie-edit.jsp").forward(request, response);
	}

}
