package io.ab.climbing.controller.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import io.ab.climbing.model.Topo;

@Results({ 
	@Result(name = "success", location = "topo.jsp"),
	@Result(name = "all", location ="topos.jsp"),
	@Result(name = "edit", location = "topo-edit.jsp"),
	@Result(name = "back", type="redirect", location = "/site/${topo.site.id}")
	})
public class TopoAction extends CustomActionSupport {
	
	private List<Topo> topos;
	
	@Override
	@Action("topo/{id}")
	public String execute() {
		this.topo = this.topoService.findOneWithOwners(id);
		return SUCCESS;
	}
	
	@Action("topo")
	public String displayAll() {
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
		this.topo = this.topoService.findOneWithOwners(id);
		return EDIT;
	}
	
	@Action("topo/edit/site")
	public String editSite() throws Exception {
		this.topoService.updateSite(this.topo.getId(), this.topo.getSite().getId());
		return BACK;
	}

	@Action("topo/{id}/edit/submit")
	public String executeEdit() throws Exception {
		this.topoService.updateOne(topo);
		return BACK;
	}

	@Action("topo/add")
	public String add() throws Exception {
		this.topoService.createOne(topo.getName(), topo.getSite().getId());
		return BACK;
	}

	public List<Topo> getTopos() {
		return topos;
	}

	public void setTopos(List<Topo> topos) {
		this.topos = topos;
	}
}
