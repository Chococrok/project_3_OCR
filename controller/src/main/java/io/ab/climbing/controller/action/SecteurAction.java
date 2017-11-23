package io.ab.climbing.controller.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ 
	@Result(name = "success", location = "secteur.jsp"), 
	@Result(name = "edit", location = "secteur-edit.jsp"),
	@Result(name = "done", type="redirect", location = "/secteur/${id}"),
	@Result(name = "back", type="redirect", location = "/site/${secteur.site.id}")
	})
public class SecteurAction extends CustomAbstractActionSupport {

	@Override
	@Action("secteur/{id}")
	public String execute() throws Exception {
		secteur = this.secteurService.findOneWithCommentsAndVoies(id);
		return SUCCESS;
	}
	
	//the method is named explode because with stupid struts2 trying to guess action name if not define
	//you can not use "delete" or "remove" 
	//see: org.apache.struts2.dispatcher.mapper.Restful2ActionMapper.java
	@Action("secteur/{id}/delete")
	public String explode() throws Exception {
		this.secteur = this.secteurService.findOne(id);
		this.secteurService.deleteOne(id);
		return BACK;
	}

	@Action("secteur/{id}/edit")
	public String edit() throws Exception {
		secteur = this.secteurService.findOneWithCommentsAndVoies(id);
		return EDIT;
	}

	@Action("secteur/{id}/edit/submit")
	public String executeEdit() throws Exception {
		this.secteurService.updateOne(secteur);
		return DONE;
	}

	@Action("secteur/{id}/comment")
	public String comment() throws Exception {
		this.secteurService.addComment(this.comment);
		return DONE;
	}

	@Action("secteur/add")
	public String add() throws Exception {
		this.secteurService.addOne(secteur);
		return BACK;
	}
}
