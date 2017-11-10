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
import io.ab.model.Secteur;
import io.ab.model.Site;

@WebServlet("/secteur/edit")
public class SecteurEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SecteurService secteurService;
	private TopoService topoService;

	@Override
    public void init() throws ServletException {
    		this.secteurService = new SecteurService(this.getServletContext());
    		this.topoService = new TopoService(this.getServletContext());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Secteur secteur = id == null ? null : this.secteurService.findOne(id);

		if (id == null || secteur == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		request.setAttribute("secteur", secteur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/secteur-edit.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action");
		
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
		
		Secteur secteur = id == null ? null : this.secteurService.findOne(id);

		if (id == null || action == null || secteur == null || action.isEmpty()) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		
		switch (action) {
		case "delete":
			this.secteurService.deleteOne(id);
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}

		request.setAttribute("secteur", secteur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/secteur-edit.jsp").forward(request, response);		
	}

}
