package io.ab.climbing.controller.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import io.ab.climbing.business.dto.SignUpForm;

@Results({
	@Result(name = "success", location = "sign-up.jsp"),
	@Result(name = "input", location = "sign-up.jsp")
})
public class RegisterAction extends CustomAbstractActionSupport implements SessionAware {
		
	private SignUpForm signUpForm;
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action("register/submit")
	public String register() throws Exception {
		this.ownerService.signUp(signUpForm);
		if (this.ownerService.hasError()) {
			this.addActionError(this.ownerService.getError());
			return INPUT;
		}
		this.session.put("owner", this.ownerService.findOneByEmail(signUpForm.getEmail()));
		return OWNER;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public SignUpForm getSignUpForm() {
		return signUpForm;
	}

	public void setSignUpForm(SignUpForm signUpForm) {
		this.signUpForm = signUpForm;
	}

}
