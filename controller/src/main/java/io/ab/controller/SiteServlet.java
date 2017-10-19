package io.ab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SiteService;
import io.ab.model.Site;

@WebServlet("/SiteServlet")
public class SiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SiteService siteService;

	@Override
    public void init() throws ServletException {
    		siteService = new SiteService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Site site = (Site) request.getAttribute("site");
		if (site == null) {
			int id = Integer.parseInt(request.getParameter("id"));
			site = this.siteService.findOneWithCommentsAndSecteurs(id);
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
