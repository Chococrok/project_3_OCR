package io.ab.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ab.business.OwnerService;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ACTION = "action";
	public static final String SIGN_IN = "signIn";
	public static final String SIGN_OUT = "signOut";

	private OwnerService ownerService;

	@Override
	public void init() throws ServletException {
		this.ownerService = new OwnerService(this.getServletContext());
	}

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
				this.ownerService.signInOwner(request);
				if (this.ownerService.hasError()) {
					request.setAttribute("error", this.ownerService.getError());
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/page/sign-in.jsp").forward(request,
							response);
					return;
				}
				response.sendRedirect(this.getServletContext().getContextPath() + "/owner");
				break;
			case SIGN_OUT:
				request.getSession().invalidate();
				response.sendRedirect(this.getServletContext().getContextPath() + "/home");
				break;
		}

	}

}
