package io.ab.business;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import io.ab.model.Entity;

public class SearchService {	
	public static final String METHOD = "method";
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String COTATION = "cotation";
	public static final String ERROR = "error";
	public static final String CONTENT = "content";

	private SiteService siteService;
	private VoieService voieService;
	private SecteurService secteurService;
	private List<Entity> entities;
	private String error;
	
	public SearchService(ServletContext context) {
		this.siteService = new SiteService(context);
		this.voieService = new VoieService(context);
		this.secteurService = new SecteurService(context);
		
	}
	
	public void search(HttpServletRequest request) {
		this.error = null;
		this.entities = new ArrayList<Entity>();
		
		if(request.getParameter(CONTENT) == null) {
			return;
		}
		
		String method = request.getParameter(METHOD);
		if(method.equals(NAME)) {
			this.findByName(request);
		} else if(method.equals(COTATION)) {
			this.findByCotation(request);
		}
		if(this.entities.isEmpty()) {
			this.error = "Nous n'avons pas trouvé l'objet de votre demande";
		}
	}
	
	public void findByName(HttpServletRequest request) {
		switch(request.getParameter(TYPE)) {
			case "site":
				this.entities = this.siteService.findEntitiesByName(request.getParameter(CONTENT));
				break;
			case "secteur":
				this.entities = this.secteurService.findEntitiesByName(request.getParameter(CONTENT));
				break;
			case "voie":
				this.entities = this.voieService.findEntitiesByName(request.getParameter(CONTENT));
				break;
				
		}
	}
	
	public void findByCotation(HttpServletRequest request) {
		switch(request.getParameter(TYPE)) {
			case "site":
				this.entities = this.siteService.findEntitiesByCotation(request.getParameter(CONTENT));
				break;
			case "secteur":
				this.entities = this.secteurService.findEntitiesByCotation(request.getParameter(CONTENT));
				break;
			case "voie":
				this.entities = this.voieService.findEntitiesByCotation(request.getParameter(CONTENT));
				break;
				
		}
	}
	
	public boolean hasError() {
		return this.error != null;
	}
	
	public String getError() {
		return this.error;
	}
	
	public List<Entity> getEntities(){
		return this.entities;
	}
}
