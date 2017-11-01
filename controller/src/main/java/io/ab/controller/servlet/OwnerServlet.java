package io.ab.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.OwnerService;
import io.ab.business.SearchService;
import io.ab.business.TopoService;
import io.ab.model.Owner;

@WebServlet("/owner")
public class OwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ACTION = "action";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String AVAILABILITY = "availability";

	private TopoService topoService;
	private OwnerService ownerService;

	@Override
	public void init() throws ServletException {
		this.topoService = new TopoService(this.getServletContext());
		this.ownerService = new OwnerService(this.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Owner owner = (Owner) request.getSession().getAttribute("owner");
		request.setAttribute("topos", this.topoService.findAllByOwner(owner.getId()));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/owner.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Owner owner = (Owner) request.getSession().getAttribute("owner");
		String action = request.getParameter(ACTION);

		switch (action) {
		case EMAIL:
			String email = request.getParameter(EMAIL);
			this.ownerService.updateEmail(email, owner.getId());
			request.getSession().setAttribute("owner", this.ownerService.findOneById(owner.getId()));
			break;
		case PHONE:
			String phone = request.getParameter(PHONE);
			this.ownerService.updatePhone(phone, owner.getId());
			request.getSession().setAttribute("owner", this.ownerService.findOneById(owner.getId()));
			break;
		case AVAILABILITY:
			int topoId = Integer.parseInt(request.getParameter("id"));
			boolean available = Boolean.parseBoolean(request.getParameter("available"));
			this.topoService.updateAvailability(owner.getId(), topoId, available);
			break;
		}
		
		request.setAttribute("topos", this.topoService.findAllByOwner(owner.getId()));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/owner.jsp").forward(request, response);
	}
}
