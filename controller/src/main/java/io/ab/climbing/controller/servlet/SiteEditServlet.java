package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.SecteurService;
import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.controller.mapper.SecteurMapper;
import io.ab.climbing.controller.mapper.SiteMapper;
import io.ab.climbing.controller.mapper.TopoMapper;
import io.ab.climbing.controller.mapper.VoieMapper;
import io.ab.climbing.model.Secteur;
import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;
import io.ab.climbing.model.Voie;

@WebServlet("/site/edit")
public class SiteEditServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private SiteService siteService;
	@Inject
	private SecteurService secteurService;
	@Inject
	private TopoService topoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);

		Site site = id == null ? null : this.siteService.findOneWithCommentsAndSecteurs(id);

		if (id == null || site == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}

		site.setTopos(this.topoService.findAllBySite(id));
		request.setAttribute("topos", this.topoService.findAll());
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
			this.siteService.deleteOne(id);
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		case "addSecteur":
			Secteur secteur = SecteurMapper.map(request);
			this.secteurService.addOne(secteur);
			response.sendRedirect(this.getServletContext().getContextPath() + "/site?id=" + id);
			return;
		case "updateSite":
			this.siteService.updateOne(SiteMapper.map(request));
			response.sendRedirect(this.getServletContext().getContextPath() + "/site?id=" + id);
			return;
		case "deleteSecteur":
			String paramSecteurId = request.getParameter("secteurId");
			Integer secteurId = paramSecteurId == null || paramSecteurId.isEmpty() ? null
					: Integer.parseInt(paramSecteurId);
			if (secteurId == null) {
				break;
			}
			this.secteurService.deleteOne(secteurId);
			site = this.siteService.findOneWithCommentsAndSecteurs(id);
			break;
		case "addExistingTopoForm":
			Topo existingTopo = TopoMapper.map(request);
			this.topoService.updateSite(existingTopo.getId(), site.getId());
			response.sendRedirect(this.getServletContext().getContextPath() + "/site?id=" + id);
			return;
		case "addNewTopoForm":
			Topo newTopo = TopoMapper.map(request);
			newTopo.setId(this.topoService.createOne(newTopo.getName(), newTopo.getSite().getId()));
			this.topoService.updateSite(newTopo.getId(), site.getId());
			response.sendRedirect(this.getServletContext().getContextPath() + "/site?id=" + id);
			return;
		case "deleteTopo":
			this.topoService.deleteSiteIdByTopo(site.getId(), TopoMapper.map(request).getId());
			response.sendRedirect(this.getServletContext().getContextPath() + "/site?id=" + id);
			return;
		}
		
		site.setTopos(this.topoService.findAllBySite(id));
		request.setAttribute("topos", this.topoService.findAll());
		request.setAttribute("site", site);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site-edit.jsp").forward(request, response);		
	}

}
