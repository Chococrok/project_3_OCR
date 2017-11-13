package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.dto.CommentDTO;

@WebServlet("/topo")
public class TopoServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private TopoService topoService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			request.setAttribute("topos", this.topoService.findAll());
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/topos.jsp").forward(request, response);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("topo", this.topoService.findOneWithOwners(id));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/topo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentDTO commentDTO = new CommentDTO(request);
		this.topoService.addComment(commentDTO);
		if (this.topoService.hasError()) {
			request.setAttribute("error", this.topoService.getError());
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("topo", this.topoService.findOneWithOwners(id));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/topo.jsp").forward(request, response);
	}

}
