package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import io.ab.climbing.business.SiteService;
import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.controller.mapper.SiteMapper;

@WebServlet("/home")
public class HomeServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	SiteService siteService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("sites", this.siteService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.siteService.addOne(SiteMapper.map(request));
		request.setAttribute("sites", this.siteService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/home.jsp").forward(request, response);
	}

}
