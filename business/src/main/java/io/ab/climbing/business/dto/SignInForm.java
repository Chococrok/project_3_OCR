package io.ab.climbing.business.dto;

import javax.servlet.http.HttpServletRequest;

public class SignInForm {

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";

	private String email;
	private String password;

	public SignInForm(HttpServletRequest request) {
		this.email = request.getParameter(EMAIL);
		this.password = request.getParameter(PASSWORD);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean hasNullOrEmpty() {
		if (this.email == null || this.password == null) {
			return true;
		}
		return this.email.isEmpty() || this.password.isEmpty();
	}

}
