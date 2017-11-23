package io.ab.climbing.controller.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import io.ab.climbing.business.dto.SignInForm;

@Results({
	@Result(name = "success", location = "sign-in.jsp"),
	@Result(name = "input", location = "sign-in.jsp")
})
public class LoginAction extends CustomAbstractActionSupport implements SessionAware {
		
	private SignInForm signInForm;
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action("login/submit")
	public String login() throws Exception {
		this.ownerService.signIn(signInForm);
		if (this.ownerService.hasError()) {
			this.addActionError(this.ownerService.getError());
			return INPUT;
		}
		this.session.put(OWNER, this.ownerService.findOneByEmail(signInForm.getEmail()));
		return OWNER;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public SignInForm getSignInForm() {
		return signInForm;
	}

	public void setSignInForm(SignInForm signInForm) {
		this.signInForm = signInForm;
	}

}
