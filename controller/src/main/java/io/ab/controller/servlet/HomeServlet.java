package io.ab.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SiteService;
import io.ab.consumer.DaoFactory;
import io.ab.controller.mapper.SiteMapper;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SiteService siteService;

	@Override
    public void init() throws ServletException {
    		siteService = new SiteService(this.getServletContext());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sites", this.siteService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.siteService.addOne(SiteMapper.map(request));
		request.setAttribute("sites", this.siteService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/home.jsp").forward(request, response);
	}

}
