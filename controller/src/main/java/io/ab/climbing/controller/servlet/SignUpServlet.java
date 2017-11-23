package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.OwnerService;
import io.ab.climbing.business.dto.SignUpForm;

@WebServlet("/signUp")
public class SignUpServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private OwnerService ownerService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-up.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//SignUpForm signUpForm = new SignUpForm(request);
		//int newOwnerId = this.ownerService.signUp(signUpForm);
		if (this.ownerService.hasError()) {
			request.setAttribute("error", this.ownerService.getError());
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-up.jsp").forward(request,
					response);
			return;
		}
		
		//request.getSession().setAttribute("owner", this.ownerService.findOneById(newOwnerId));
		response.sendRedirect(this.getServletContext().getContextPath() + "/owner");
	}

}