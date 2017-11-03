package io.ab.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SiteService;
import io.ab.business.TopoService;
import io.ab.model.Site;

@WebServlet("/site")
public class SiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SiteService siteService;
	private TopoService topoService;

	@Override
    public void init() throws ServletException {
    		this.siteService = new SiteService(this.getServletContext());
    		this.topoService = new TopoService(this.getServletContext());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Site site = (Site) request.getAttribute("site");
		if (site == null) {
			int id = Integer.parseInt(request.getParameter("id"));
			site = this.siteService.findOneWithCommentsAndSecteurs(id);
			request.setAttribute("topos", this.topoService.findAllBySite(site.getId()));
			request.setAttribute("site", site);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id = Integer.parseInt(request.getParameter("id"));
		this.siteService.addComment(id, request.getParameter("content"));

		request.setAttribute("site", this.siteService.findOneWithCommentsAndSecteurs(id));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site.jsp").forward(request, response);
	}

}
