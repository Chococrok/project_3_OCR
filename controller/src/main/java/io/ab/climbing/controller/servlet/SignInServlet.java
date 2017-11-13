package io.ab.climbing.controller.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.climbing.business.OwnerService;
import io.ab.climbing.business.dto.SignInForm;

@WebServlet("/signIn")
public class SignInServlet extends AbstractInjectionServlet {
	private static final long serialVersionUID = 1L;

	public static final String ACTION = "action";
	public static final String SIGN_IN = "signIn";
	public static final String SIGN_OUT = "signOut";

	@Inject
	private OwnerService ownerService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-in.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter(ACTION);
		switch (action) {
		
		case SIGN_IN:
			SignInForm signInForm = new SignInForm(request);
			this.ownerService.signIn(signInForm);
			if (this.ownerService.hasError()) {
				request.setAttribute("error", this.ownerService.getError());
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-in.jsp").forward(request,
						response);
				return;
			}
			request.getSession().setAttribute("owner", this.ownerService.findOneByEmail(signInForm.getEmail()));
			response.sendRedirect(this.getServletContext().getContextPath() + "/owner");
			break;
			
		case SIGN_OUT:
			request.getSession().invalidate();
			response.sendRedirect(this.getServletContext().getContextPath() + "/home");
			break;
		}

	}

}
