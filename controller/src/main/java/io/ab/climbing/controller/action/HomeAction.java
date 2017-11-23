package io.ab.climbing.controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import io.ab.climbing.business.SiteService;
import io.ab.climbing.model.Site;

public class HomeAction extends CustomAbstractActionSupport {
	
	private List<Site> sites;

	@Override
	public String execute() throws Exception {
		this.sites = this.siteService.findAll();
		return SUCCESS;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
}
