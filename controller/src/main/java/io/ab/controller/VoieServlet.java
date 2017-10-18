package io.ab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.VoieService;

@WebServlet("/VoieServlet")
public class VoieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	VoieService voieService;

	@Override
    public void init() throws ServletException {
		voieService = new VoieService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("voie", this.voieService.findOneWithCommentsAndLongueurs(id));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id = Integer.parseInt(request.getParameter("id"));
		this.voieService.addComment(id, request.getParameter("content"));

		request.setAttribute("voie", this.voieService.findOneWithCommentsAndLongueurs(id));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie.jsp").forward(request, response);
	}

}
