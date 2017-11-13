package io.ab.climbing.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class AbstractInjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// inject productService dependency
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
