package io.ab.climbing.controller.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction extends CustomAbstractActionSupport implements SessionAware {
		
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		this.session.remove(OWNER);
		return HOME;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}
}
