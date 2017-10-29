package io.ab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.TopoService;

@WebServlet("/topo")
public class TopoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TopoService topoService;

	@Override
	public void init() throws ServletException {
		topoService = new TopoService(this.getServletContext());
	}

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
