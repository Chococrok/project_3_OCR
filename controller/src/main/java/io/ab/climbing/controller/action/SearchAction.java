package io.ab.climbing.controller.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import io.ab.climbing.business.dto.SearchForm;
import io.ab.climbing.model.Entity;

@Result(location = "search.jsp")
public class SearchAction extends CustomAbstractActionSupport {
	
	private SearchForm searchForm;
	private List<Entity> entities;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action("search/submit")
	public String search() throws Exception {
		this.searchService.search(this.searchForm);
		
		this.addActionError(this.searchService.getError());
		this.entities = this.searchService.getEntities();
		return SUCCESS;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

}
