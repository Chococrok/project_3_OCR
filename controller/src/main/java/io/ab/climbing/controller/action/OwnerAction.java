package io.ab.climbing.controller.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

import io.ab.climbing.model.Owner;
import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;

@ParentPackage(value ="loginPackage")
@InterceptorRef("loginStack")
@Results({ 
		@Result(name = "success", location = "owner.jsp"),
		@Result(name = "edit", location = "owner-edit.jsp"),
		@Result(name = "logout", type = "redirect", location ="/logout")
		})
public class OwnerAction extends CustomAbstractActionSupport implements SessionAware {
	
	public static final String LOGOUT = "logout";

	private List<Topo> toposOwned;
	private List<Topo> toposNotOwned;
	private List<Site> sites;

	@Override
	public String execute() throws Exception {
		this.toposOwned = this.topoService.findAllByOwner(owner.getId());
		this.toposNotOwned = this.topoService.findAllByNotOwner(owner.getId());
		this.sites = this.siteService.findAll();
		return SUCCESS;
	}

	// the method is named explode because with stupid struts2 trying to guess
	// action name if not define
	// you can not use "delete" or "remove"
	// see: org.apache.struts2.dispatcher.mapper.Restful2ActionMapper.java
	@Action("owner/delete")
	public String explode() throws Exception {
		this.ownerService.deleteOne(owner.getId());
		return LOGOUT;
	}

	@Action("owner/edit")
	public String edit() throws Exception {
		this.ownerService.updateOne(owner);
		return OWNER;
	}

	@Action("owner/topo/add")
	public String add() throws Exception {
		this.ownerService.addTopo(topo, owner);
		return OWNER;
	}
	
	@Action("owner/topo/available")
	public String updateAvailability() {
		this.topoService.updateAvailability(owner.getId(), topo.getId(), topo.isAvailable());
		return OWNER;
	}

	public List<Topo> getToposOwned() {
		return toposOwned;
	}

	public void setToposOwned(List<Topo> toposOwned) {
		this.toposOwned = toposOwned;
	}

	public List<Topo> getToposNotOwned() {
		return toposNotOwned;
	}

	public void setToposNotOwned(List<Topo> toposNotOwned) {
		this.toposNotOwned = toposNotOwned;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.owner = (Owner) session.get("owner");
		
	}
}
