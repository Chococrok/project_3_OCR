package io.ab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SearchService;
import io.ab.business.SiteService;
import io.ab.model.Site;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SearchService searchService;

	@Override
    public void init() throws ServletException {
    		searchService = new SearchService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		this.searchService.search(request);

		request.setAttribute(SearchService.ERROR, this.searchService.getError());
		request.setAttribute("entities", this.searchService.getEntities());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/search.jsp").forward(request, response);
		/*StringBuilder path = new StringBuilder(this.getServletContext().getContextPath());
		path.append("/");
		path.append(request.getParameter(SearchService.TYPE));
		path.append("?id=");
		path.append(this.searchService.getId());
		response.sendRedirect(path.toString());*/
	}
}
