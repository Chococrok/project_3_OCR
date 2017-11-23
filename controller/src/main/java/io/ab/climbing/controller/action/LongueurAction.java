package io.ab.climbing.controller.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ 
	@Result(name = "edit", location = "longueur-edit.jsp"),
	@Result(name = "back", type="redirect", location = "/voie/${longueur.voie.id}")
	})
public class LongueurAction extends CustomAbstractActionSupport {
	
	//the method is named explode because with stupid struts2 trying to guess action name if not define
	//you can not use "delete" or "remove" 
	//see: org.apache.struts2.dispatcher.mapper.Restful2ActionMapper.java
	@Action("longueur/{id}/delete")
	public String explode() throws Exception {
		this.longueur = this.longueurService.findOne(id);
		this.longueurService.deleteOne(id);
		return BACK;
	}

	@Action("longueur/{id}/edit")
	public String edit() throws Exception {
		this.longueur = this.longueurService.findOne(id);
		return EDIT;
	}

	@Action("longueur/{id}/edit/submit")
	public String executeEdit() throws Exception {
		this.longueurService.updateOne(longueur);
		return BACK;
	}

	@Action("longueur/add")
	public String add() throws Exception {
		this.longueurService.addOne(longueur);
		return BACK;
	}
}
