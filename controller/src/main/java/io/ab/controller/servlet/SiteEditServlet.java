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
import io.ab.business.dto.CommentDTO;
import io.ab.model.Site;

@WebServlet("/site/edit")
public class SiteEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SiteService siteService;
	private TopoService topoService;

	@Override
    public void init() throws ServletException {
    		this.siteService = new SiteService(this.getServletContext());
    		this.topoService = new TopoService(this.getServletContext());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Site site = id == null ? null : this.siteService.findOneWithCommentsAndSecteurs(id);

		if (id == null || site == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		request.setAttribute("topos", this.topoService.findAll());
		request.setAttribute("toposSite", this.topoService.findAllBySite(site.getId()));
		request.setAttribute("site", site);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site-edit.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action");
		
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
		
		Site site = id == null ? null : this.siteService.findOneWithCommentsAndSecteurs(id);

		if (id == null || action == null || site == null || action.isEmpty()) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		
		switch (action) {
		case "delete":
			System.out.println(id);
			this.siteService.deleteOne(id);
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		
		request.setAttribute("topos", this.topoService.findAll());
		request.setAttribute("toposSite", this.topoService.findAllBySite(site.getId()));
		request.setAttribute("site", site);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site-edit.jsp").forward(request, response);		
	}

}
