package io.ab.climbing.controller.action;

import java.util.List;

import javax.inject.Inject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import io.ab.climbing.business.SecteurService;
import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.model.Secteur;
import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;

@Results({ 
	@Result(name = "success", location = "site.jsp"), 
	@Result(name = "edit", location = "site-edit.jsp"),
	@Result(name = "done", type="redirect", location = "/site/${id}")
	})
public class SiteAction extends CustomActionSupport {
	
	private List<Topo> topos;

	@Override
	@Action("site/{id}")
	public String execute() throws Exception {
		site = this.siteService.findOneWithCommentsAndSecteurs(id);
		site.setTopos(this.topoService.findAllBySite(id));
		return SUCCESS;
	}
	
	//the method is named explode because with stupid struts2 trying to guess action name if not define
	//you can not use "delete" or "remove" 
	//see: org.apache.struts2.dispatcher.mapper.Restful2ActionMapper.java
	@Action("site/{id}/delete")
	public String explode() throws Exception {
		this.siteService.deleteOne(id);
		return INPUT;
	}

	@Action("site/{id}/edit")
	public String edit() throws Exception {
		site = this.siteService.findOneWithCommentsAndSecteurs(id);
		site.setTopos(this.topoService.findAllBySite(id));
		this.topos = this.topoService.findAll();
		return EDIT;
	}

	@Action("site/{id}/edit/submit")
	public String executeEdit() throws Exception {
		this.siteService.updateOne(site);
		return DONE;
	}

	@Action("site/{id}/comment")
	public String comment() throws Exception {
		this.siteService.addComment(this.comment);
		return DONE;
	}

	@Action("site/add")
	public String add() throws Exception {
		this.siteService.addOne(site);
		return INPUT;
	}

	public List<Topo> getTopos() {
		return topos;
	}

	public void setTopos(List<Topo> topos) {
		this.topos = topos;
	}
}
