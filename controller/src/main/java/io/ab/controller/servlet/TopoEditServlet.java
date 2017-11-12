package io.ab.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.SiteService;
import io.ab.business.TopoService;
import io.ab.business.dto.CommentDTO;
import io.ab.controller.mapper.TopoMapper;
import io.ab.model.Topo;

@WebServlet("/topo/edit")
public class TopoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TopoService topoService;
	private SiteService siteService;

	@Override
	public void init() throws ServletException {
		topoService = new TopoService(this.getServletContext());
		siteService = new SiteService(this.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect(this.getServletContext().getContextPath() + "/topo");
			return;
		}
		
		request.setAttribute("sites", this.siteService.findAll());
		request.setAttribute("topo", this.topoService.findOneWithOwners(id));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/topo-edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect(this.getServletContext().getContextPath() + "/topo");
			return;
		}
		String action = request.getParameter("action");
		
		switch (action) {
		case "editTopo":
			this.topoService.updateOne(TopoMapper.map(request));
			response.sendRedirect(this.getServletContext().getContextPath() + "/topo?id=" + id);
			return;
		case "delete":
			this.topoService.deleteOne(id);
			response.sendRedirect(this.getServletContext().getContextPath() + "/topo");
			return;
		case "changeSite":
			Topo topo = TopoMapper.map(request);
			this.topoService.updateSite(topo.getId(), topo.getSite().getId());
			response.sendRedirect(this.getServletContext().getContextPath() + "/topo?id=" + id);
			return;
		}
		
		request.setAttribute("sites", this.siteService.findAll());
		request.setAttribute("topo", this.topoService.findOneWithOwners(id));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/topo-edit.jsp").forward(request, response);
	}

}
