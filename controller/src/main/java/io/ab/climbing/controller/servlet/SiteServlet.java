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
import io.ab.climbing.model.Site;

@WebServlet("/site")
public class SiteServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SiteService siteService;
	@Inject
	private TopoService topoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
		
		Site site = id == null ? null : this.siteService.findOneWithCommentsAndSecteurs(id);

		if (id == null || site == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		
		site.setTopos(this.topoService.findAllBySite(id));
		request.setAttribute("site", site);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
		
		Site site = id == null ? null : this.siteService.findOneWithCommentsAndSecteurs(id);

		if (id == null || site == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		
		CommentDTO commentDTO = new CommentDTO();
		this.siteService.addComment(commentDTO);

		site.setTopos(this.topoService.findAllBySite(id));
		request.setAttribute("site", this.siteService.findOneWithCommentsAndSecteurs(id));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/site.jsp").forward(request, response);
	}

}
