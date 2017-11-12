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
import io.ab.controller.mapper.SecteurMapper;
import io.ab.controller.mapper.SiteMapper;
import io.ab.controller.mapper.TopoMapper;
import io.ab.controller.mapper.VoieMapper;
import io.ab.model.Secteur;
import io.ab.model.Site;
import io.ab.model.Topo;
import io.ab.model.Voie;

@WebServlet("/site/edit")
public class SiteEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SiteService siteService;
	private SecteurService secteurService;
	private TopoService topoService;

	@Override
    public void init() throws ServletException {
    		this.siteService = new SiteService(this.getServletContext());
    		this.secteurService = new SecteurService(this.getServletContext());
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
