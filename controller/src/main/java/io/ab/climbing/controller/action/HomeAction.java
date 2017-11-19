package io.ab.climbing.controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import io.ab.climbing.business.SiteService;
import io.ab.climbing.model.Site;

public class HomeAction extends ActionSupport {
	
	@Inject
	SiteService siteService;
	
	public List<Site> sites;

	@Override
	public String execute() throws Exception {
		this.sites = this.siteService.findAll();
		return SUCCESS;
	}
}
