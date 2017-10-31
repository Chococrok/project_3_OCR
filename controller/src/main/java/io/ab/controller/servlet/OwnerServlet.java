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
	
	private TopoService topoService;
	private OwnerService ownerService;

	@Override
    public void init() throws ServletException {
		this.topoService = new TopoService(this.getServletContext());
		this.ownerService = new OwnerService(this.getServletContext());
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Owner owner = (Owner) request.getSession().getAttribute("owner");
		request.setAttribute("topos", this.topoService.findAllByOwner(owner.getId()));
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/owner.jsp").forward(request, response);
	}
}
