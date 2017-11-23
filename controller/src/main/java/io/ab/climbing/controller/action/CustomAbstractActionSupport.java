package io.ab.climbing.controller.action;

import javax.inject.Inject;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import io.ab.climbing.business.LongueurService;
import io.ab.climbing.business.OwnerService;
import io.ab.climbing.business.SearchService;
import io.ab.climbing.business.SecteurService;
import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.VoieService;
import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.model.Longueur;
import io.ab.climbing.model.Owner;
import io.ab.climbing.model.Secteur;
import io.ab.climbing.model.Site;
import io.ab.climbing.model.Topo;
import io.ab.climbing.model.Voie;

@Results({
	@Result(name = "input", type = "redirect", location = "/home"),
	@Result(name = "home", type = "redirect", location = "/home"),
	@Result(name = "owner", type = "redirect", location = "/owner")
	})
public abstract class CustomAbstractActionSupport extends ActionSupport {
	
	public static final String DONE = "done";
	public static final String EDIT = "edit";
	public static final String BACK = "back";
	public static final String ALL = "all";
	public static final String OWNER = "owner";
	public static final String HOME = "home";
	
	@Inject
	protected SiteService siteService;
	@Inject
	protected TopoService topoService;
	@Inject
	protected SecteurService secteurService;
	@Inject
	protected LongueurService longueurService;
	@Inject 
	protected OwnerService ownerService;
	@Inject 
	protected SearchService searchService;
	@Inject 
	protected VoieService voieService;
	
	protected Integer id;
	protected Site site;
	protected Secteur secteur;
	protected Voie voie;
	protected Longueur longueur;
	protected Owner owner;
	protected Topo topo;
	protected CommentDTO comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

	public Longueur getLongueur() {
		return longueur;
	}

	public void setLongueur(Longueur longueur) {
		this.longueur = longueur;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public CommentDTO getComment() {
		return comment;
	}

	public void setComment(CommentDTO comment) {
		this.comment = comment;
	}

}
