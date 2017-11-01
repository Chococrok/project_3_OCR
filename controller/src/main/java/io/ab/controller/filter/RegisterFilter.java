package io.ab.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter( urlPatterns="/owner/*")
public class RegisterFilter implements Filter {
	public static final String SIGN_IN = "/signIn";
	
	//default constructor needed by WebFilter.
    public RegisterFilter(){}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		boolean signedIn = ((HttpServletRequest) request).getSession().getAttribute("owner") != null;
		if(signedIn) {
			chain.doFilter(request, response);
			return;
		}
		
		request.getRequestDispatcher( SIGN_IN ).forward( request, response );
		
	}

}
