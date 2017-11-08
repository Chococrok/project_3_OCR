package io.ab.business.dto;

import javax.servlet.http.HttpServletRequest;

public class SearchForm {
	public static final String METHOD = "method";
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String COTATION = "cotation";
	public static final String CONTENT = "content";
	
	public static final String VOIE = "voie";
	public static final String SECTEUR = "secteur";
	public static final String SITE = "site";

	private String method;
	private String type;
	private String content;

	public SearchForm(HttpServletRequest request) {
		this.method = request.getParameter(METHOD);
		this.type = request.getParameter(TYPE);
		this.content = request.getParameter(CONTENT);
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean hasNullOrEmpty() {
		if (this.content == null || this.method == null || this.type == null) {
			return true;
		}
		return this.content.isEmpty() || this.method.isEmpty() || this.type.isEmpty();
	}

}
