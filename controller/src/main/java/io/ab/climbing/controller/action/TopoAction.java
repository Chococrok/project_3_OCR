package io.ab.climbing.controller.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;

@Results({ 
	@Result(name = "success", location = "topo.jsp"),
	@Result(name = "all", location ="topos.jsp"),
	@Result(name = "edit", location = "topo-edit.jsp"),
	@Result(name = "back", type="redirect", location = "/site/${topo.site.id}"),
	@Result(name = "self", type = "redirect", location = "topo/${id}")
	})
public class TopoAction extends CustomAbstractActionSupport {
	
	public static final String SELF = "self";
	
	private List<Topo> topos;
	private List<Site> sites;
	
	@Override
	@Action("topo/{id}")
	public String execute() throws Exception {
		this.topo = this.topoService.findOneWithOwners(id);
		return SUCCESS;
	}
	
	@Action("topo")
	public String displayAll() throws Exception {
		this.topos = this.topoService.findAll();
		return ALL;
	}
	
	//the method is named explode because with stupid struts2 trying to guess action name if not define
	//you can not use "delete" or "remove" 
	//see: org.apache.struts2.dispatcher.mapper.Restful2ActionMapper.java
	@Action("topo/{id}/delete")
	public String explode() throws Exception {
		this.topo = this.topoService.findOneWithOwners(id);
		this.topoService.deleteOne(id);
		return BACK;
	}

	@Action("topo/{id}/edit")
	public String edit() throws Exception {
		this.sites = this.siteService.findAll();
		this.topo = this.topoService.findOneWithOwners(id);
		return EDIT;
	}
	
	@Actions({
		@Action("topo/edit/site"),
		@Action(value = "topo/{id}/edit/site", results = @Result(name = BACK, type = "redirect", location = "topo/${id}"))
		})
	public String editSite() throws Exception {
		this.topoService.updateSite(this.topo.getId(), this.topo.getSite().getId());
		return BACK;
	}

	@Action("topo/{id}/edit/submit")
	public String executeEdit() throws Exception {
		this.topoService.updateOne(topo);
		return SELF;
	}

	@Action("topo/add")
	public String add() throws Exception {
		this.topoService.createOne(topo.getName(), topo.getSite().getId());
		return BACK;
	}
	
	@Action("topo/new")
	public String addNew() throws Exception {
		this.topoService.createOne(topo.getName(), topo.getSite().getId());
		return BACK;
	}
	
	@Action("topo/{id}/comment")
	public String comment() throws Exception {
		this.topoService.addComment(comment);
		return SELF;
	}

	public List<Topo> getTopos() {
		return topos;
	}

	public void setTopos(List<Topo> topos) {
		this.topos = topos;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
}
