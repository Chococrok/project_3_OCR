package io.ab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SecteurService;

@WebServlet("/SecteurServlet")
public class SecteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SecteurService secteurService;

	@Override
    public void init() throws ServletException {
    		secteurService = new SecteurService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("secteur", this.secteurService.findOneWithCommentsAndVoies(id));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/secteur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id = Integer.parseInt(request.getParameter("id"));
		this.secteurService.addComment(id, request.getParameter("content"));

		request.setAttribute("secteur", this.secteurService.findOneWithCommentsAndVoies(id));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/secteur.jsp").forward(request, response);
	}

}