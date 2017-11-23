package io.ab.climbing.controller.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ 
	@Result(name = "success", location = "voie.jsp"), 
	@Result(name = "edit", location = "voie-edit.jsp"),
	@Result(name = "done", type="redirect", location = "/voie/${id}"),
	@Result(name = "back", type="redirect", location = "/secteur/${voie.secteur.id}")
	})
public class VoieAction extends CustomAbstractActionSupport {

	@Override
	@Action("voie/{id}")
	public String execute() throws Exception {
		this.voie = this.voieService.findOneWithCommentsAndLongueurs(id);
		return SUCCESS;
	}
	
	//the method is named explode because with stupid struts2 trying to guess action name if not define
	//you can not use "delete" or "remove" 
	//see: org.apache.struts2.dispatcher.mapper.Restful2ActionMapper.java
	@Action("voie/{id}/delete")
	public String explode() throws Exception {
		this.voie = this.voieService.findOne(id);
		this.voieService.deleteOne(id);
		return BACK;
	}

	@Action("voie/{id}/edit")
	public String edit() throws Exception {
		this.voie = this.voieService.findOneWithCommentsAndLongueurs(id);
		return EDIT;
	}

	@Action("voie/{id}/edit/submit")
	public String executeEdit() throws Exception {
		this.voieService.updateOne(voie);
		return DONE;
	}

	@Action("voie/{id}/comment")
	public String comment() throws Exception {
		this.voieService.addComment(this.comment);
		return DONE;
	}

	@Action("voie/add")
	public String add() throws Exception {
		this.voieService.addOne(voie);
		return BACK;
	}
}
