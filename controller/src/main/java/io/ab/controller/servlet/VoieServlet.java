package io.ab.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.VoieService;
import io.ab.business.dto.CommentDTO;

@WebServlet("/voie")
public class VoieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	VoieService voieService;

	@Override
    public void init() throws ServletException {
		voieService = new VoieService(this.getServletContext());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
		if (id == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		request.setAttribute("voie", this.voieService.findOneWithCommentsAndLongueurs(id));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		CommentDTO commentDTO = new CommentDTO(request);

		this.voieService.addComment(commentDTO);

		request.setAttribute("voie", this.voieService.findOneWithCommentsAndLongueurs(commentDTO.getEntityId()));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/voie.jsp").forward(request, response);
	}

}
