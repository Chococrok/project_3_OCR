package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import io.ab.climbing.business.SearchService;
import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.dto.SearchForm;
import io.ab.climbing.model.Site;

@WebServlet("/search")
public class SearchServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	SearchService searchService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SearchForm searchForm = new SearchForm(request);
		this.searchService.search(searchForm);

		request.setAttribute(SearchService.ERROR, this.searchService.getError());
		request.setAttribute("entities", this.searchService.getEntities());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/search.jsp").forward(request, response);
	}
}
