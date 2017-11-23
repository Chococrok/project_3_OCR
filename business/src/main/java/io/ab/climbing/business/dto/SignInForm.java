package io.ab.climbing.business.dto;

public class SignInForm {

	private String email;
	private String password;

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
