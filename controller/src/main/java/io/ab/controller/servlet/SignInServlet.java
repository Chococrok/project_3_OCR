package io.ab.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.OwnerService;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OwnerService ownerService;
	
	
	@Override
    public void init() throws ServletException {
		this.ownerService = new OwnerService(this.getServletContext());
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-in.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ownerService.signInOwner(request);
		
		if(this.ownerService.hasError()) {
			request.setAttribute("error", this.ownerService.getError());
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-in.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(this.getServletContext().getContextPath() + "/owner");
		
	}
	
	

}
