package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.OwnerService;
import io.ab.climbing.business.SearchService;
import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.dto.AddTopoForm;
import io.ab.climbing.controller.mapper.OwnerMapper;
import io.ab.climbing.model.Owner;

@WebServlet("/owner")
public class OwnerServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	public static final String ACTION = "action";
	public static final String OWNER = "owner";
	public static final String AVAILABILITY = "availability";
	public static final String TOPO = "topo";

	private static final String DELETE = "delete";

	@Inject
	private TopoService topoService;
	@Inject
	private OwnerService ownerService;
	@Inject
	private SiteService siteService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Owner owner = (Owner) request.getSession().getAttribute("owner");
		request.setAttribute("toposOwned", this.topoService.findAllByOwner(owner.getId()));
		request.setAttribute("toposNotOwned", this.topoService.findAllByNotOwner(owner.getId()));
		request.setAttribute("sites", this.siteService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/owner.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Owner owner = (Owner) request.getSession().getAttribute("owner");
		String action = request.getParameter(ACTION);

		switch (action) {
		case OWNER:
			owner = OwnerMapper.map(request);
			this.ownerService.updateOne(owner);
			request.getSession().setAttribute("owner", owner);
			break;
		case AVAILABILITY:
			String paramId = request.getParameter("id");
			Integer id = paramId == null || paramId.isEmpty() ? null : Integer.parseInt(paramId);
			String paramAvailable = request.getParameter("available");
			Boolean available = paramAvailable == null ? null : Boolean.parseBoolean(paramAvailable);
			if (id == null || available == null) {
				break;
			}
			this.topoService.updateAvailability(owner.getId(), id, available);
			break;
		case TOPO:
			AddTopoForm topoForm = new AddTopoForm(request);
			//this.ownerService.addTopo(topoForm, owner);
			if (this.ownerService.hasError()) {
				request.setAttribute("error", this.ownerService.getError());
			}
			break;
		case DELETE:
			this.ownerService.deleteOne(owner.getId());
			request.getSession().invalidate();
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			return;
		}
		
		request.setAttribute("toposOwned", this.topoService.findAllByOwner(owner.getId()));
		request.setAttribute("toposNotOwned", this.topoService.findAllByNotOwner(owner.getId()));
		request.setAttribute("sites", this.siteService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/owner.jsp").forward(request, response);
	}
}
