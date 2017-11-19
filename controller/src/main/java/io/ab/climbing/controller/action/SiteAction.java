package io.ab.climbing.controller.action;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.dispatcher.RequestMap;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import io.ab.climbing.business.SiteService;
import io.ab.climbing.business.TopoService;
import io.ab.climbing.business.dto.CommentDTO;
import io.ab.climbing.model.Site;

@Results({ @Result(name = "success", location = "site.jsp"), @Result(name = "edit", location = "site-edit.jsp") })
public class SiteAction extends ActionSupport {
	public static final String EDIT = "edit";

	@Inject
	private SiteService siteService;
	@Inject
	private TopoService topoService;

	private String url;
	private Integer id;
	private Site site;
	private CommentDTO comment;

	@Override
	@Action("site/{id}")
	public String execute() throws Exception {
		site = this.siteService.findOneWithCommentsAndSecteurs(id);
		site.setTopos(this.topoService.findAllBySite(id));
		return SUCCESS;
	}

	@Action("site/{id}/edit")
	public String edit() throws Exception {
		site = this.siteService.findOneWithCommentsAndSecteurs(id);
		site.setTopos(this.topoService.findAllBySite(id));
		return EDIT;
	}

	@Action("site/{id}/edit/submit")
	public void executeEdit() throws Exception {
		this.siteService.updateOne(site);
		String contextPath = ServletActionContext.getServletContext().getContextPath();
		ServletActionContext.getResponse().sendRedirect(contextPath + "/site/" + this.id);
	}

	@Action("site/{id}/comment")
	public void comment() throws Exception {
		this.siteService.addComment(this.comment);
		String contextPath = ServletActionContext.getServletContext().getContextPath();
		ServletActionContext.getResponse().sendRedirect(contextPath + "/site/" + this.id + "#comment");
	}

	@Action(value = "site/add", results = @Result(type = "redirect", location = "/home"))
	public String add() throws Exception {
		this.siteService.addOne(site);
		return SUCCESS;
	}
	
	@Action("site/{id}/delete")
	public void delete() throws Exception {
		this.siteService.deleteOne(id);
		String contextPath = ServletActionContext.getServletContext().getContextPath();
		ServletActionContext.getResponse().sendRedirect(contextPath + "/home");
	}

	public String getUrl() {
		return this.url;
	}

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

	public CommentDTO getComment() {
		return comment;
	}

	public void setComment(CommentDTO comment) {
		this.comment = comment;
	}
}
