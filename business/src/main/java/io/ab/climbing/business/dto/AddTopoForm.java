package io.ab.climbing.business.dto;

import javax.servlet.http.HttpServletRequest;

public class AddTopoForm {

	private static final String TOPO_NAME = "topoName";
	private static final String SITE_NAME = "siteName";
	private static final String TOPO_ID = "topoId";
	private static final String SITE_ID = "siteId";
	
	private Integer topoId;
	private String topoName;
	private Integer siteId;
	private String siteName;

	public AddTopoForm(HttpServletRequest request) {
		topoId = request.getParameter(TOPO_ID) == null ? null : Integer.parseInt(request.getParameter(TOPO_ID));
		topoName = request.getParameter(TOPO_NAME);
		siteId = request.getParameter(SITE_ID) == null ? null : Integer.parseInt(request.getParameter(SITE_ID));
		siteName = request.getParameter(SITE_NAME);
	}

	public Integer getTopoId() {
		return topoId;
	}

	public void setTopoId(Integer topoId) {
		this.topoId = topoId;
	}

	public String getTopoName() {
		return topoName;
	}

	public void setTopoName(String topoName) {
		this.topoName = topoName;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
