package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.SecteurService;
import io.ab.climbing.business.dto.CommentDTO;

@WebServlet("/secteur")
public class SecteurServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	SecteurService secteurService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
		if (id == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		request.setAttribute("secteur", this.secteurService.findOneWithCommentsAndVoies(id));

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/secteur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDTO commentDTO = new CommentDTO();
		this.secteurService.addComment(commentDTO);

		request.setAttribute("secteur", this.secteurService.findOneWithCommentsAndVoies(commentDTO.getEntityId()));

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/secteur.jsp").forward(request, response);
	}

}
