package io.ab.business;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import io.ab.business.dto.SearchForm;
import io.ab.model.Entity;

public class SearchService {	

	public static final String ERROR = "error";

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
	
	public void search(SearchForm searchForm) {
		this.error = null;
		this.entities = new ArrayList<Entity>();
		
		if(searchForm.hasNullOrEmpty()) {
			this.error = "Tout les champs doivent être renseignés";
			return;
		}
		
		if(searchForm.getMethod().equals(SearchForm.NAME)) {
			this.findByName(searchForm);
		} else if(searchForm.getMethod().equals(SearchForm.COTATION)) {
			this.findByCotation(searchForm);
		}
		if(this.entities.isEmpty()) {
			this.error = "Nous n'avons pas trouvé l'objet de votre demande";
		}
	}
	
	private void findByName(SearchForm searchForm) {
		switch(searchForm.getType()) {
			case SearchForm.SITE:
				this.entities = this.siteService.findEntitiesByName(searchForm.getContent());
				break;
			case SearchForm.SECTEUR:
				this.entities = this.secteurService.findEntitiesByName(searchForm.getContent());
				break;
			case SearchForm.VOIE:
				this.entities = this.voieService.findEntitiesByName(searchForm.getContent());
				break;
				
		}
	}
	
	private void findByCotation(SearchForm searchForm) {
		switch(searchForm.getType()) {
			case SearchForm.SITE:
				this.entities = this.siteService.findEntitiesByCotation(searchForm.getContent());
				break;
			case SearchForm.SECTEUR:
				this.entities = this.secteurService.findEntitiesByCotation(searchForm.getContent());
				break;
			case SearchForm.VOIE:
				this.entities = this.voieService.findEntitiesByCotation(searchForm.getContent());
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
