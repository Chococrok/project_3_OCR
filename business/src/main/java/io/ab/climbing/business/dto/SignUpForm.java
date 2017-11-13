package io.ab.climbing.business.dto;

import javax.servlet.http.HttpServletRequest;

public class SignUpForm {

	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String CONFIRM_PASSWORD = "confirmPassword";

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;

	public SignUpForm(HttpServletRequest request) {
		firstName = request.getParameter(FIRST_NAME);
		lastName = request.getParameter(LAST_NAME);
		email = request.getParameter(EMAIL);
		password = request.getParameter(PASSWORD);
		confirmPassword = request.getParameter(CONFIRM_PASSWORD);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public boolean passwordIsMatching() {
		return this.password.equals(this.confirmPassword);
	}

	public boolean hasNullOrEmpty() {
		if (this.firstName == null || this.lastName == null || this.email == null || this.password == null
				|| this.confirmPassword == null) {
			return true;
		}
		return firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()
				|| confirmPassword.isEmpty();
	}

}
