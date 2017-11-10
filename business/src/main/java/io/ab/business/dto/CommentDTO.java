package io.ab.business.dto;

import javax.servlet.http.HttpServletRequest;

public class CommentDTO {

	private static final String CONTENT = "content";
	private static final String ID = "id";

	private Integer entityId;
	private String content;

	public CommentDTO(HttpServletRequest request) {
		this.entityId = request.getParameter(ID) == null ? null : Integer.parseInt(request.getParameter(ID));
		this.content = request.getParameter(CONTENT);
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean hasNullOrEmpty() {
		if (this.content == null || this.entityId == null) {
			return true;
		}
		
		return this.content.trim().isEmpty();
	}

}