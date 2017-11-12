package io.ab.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.OwnerService;
import io.ab.business.SearchService;
import io.ab.business.SiteService;
import io.ab.business.TopoService;
import io.ab.business.dto.AddTopoForm;
import io.ab.controller.mapper.OwnerMapper;
import io.ab.model.Owner;

@WebServlet("/owner")
public class OwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ACTION = "action";
	public static final String OWNER = "owner";
	public static final String AVAILABILITY = "availability";
	public static final String TOPO = "topo";

	private static final String DELETE = "delete";

	private TopoService topoService;
	private OwnerService ownerService;
	private SiteService siteService;

	@Override
	public void init() throws ServletException {
		this.topoService = new TopoService(this.getServletContext());
		this.ownerService = new OwnerService(this.getServletContext());
		this.siteService = new SiteService(this.getServletContext());
	}

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
			this.ownerService.addTopo(topoForm, owner);
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
