package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.controller.mapper.TopoMapper;
import io.ab.climbing.model.Topo;

@WebServlet("/topo/edit")
public class TopoEditServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private TopoService topoService;
	@Inject
	private SiteService siteService;

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
