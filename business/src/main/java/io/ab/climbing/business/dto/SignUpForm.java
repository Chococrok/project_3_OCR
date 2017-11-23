package io.ab.climbing.business.dto;


public class SignUpForm {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
