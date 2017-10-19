package io.ab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SiteService;
import io.ab.model.Site;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SiteService siteService;

	@Override
    public void init() throws ServletException {
    		siteService = new SiteService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String name = request.getParameter("name");
		if (name == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/search.jsp").forward(request, response);
			return;
		}
		Site site = this.siteService.findOneByName(name);
		if (site.getId() == null) {
			request.setAttribute("error", "Nous n'avons pas trouvé l'objet de votre demande");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/search.jsp").forward(request, response);
			return;
		}
		request.setAttribute("site", site);
		this.getServletContext().getRequestDispatcher("/site").forward(request, response);
	}
}
