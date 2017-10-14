package io.ab.business;

import java.util.List;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.SiteDao;
import io.ab.model.Site;

public class SiteService {
	
	private SiteDao siteDao;
	
	public SiteService() {
		this.siteDao = DaoFactory.getInstance().getSiteDao();
	}

	public List<Site> findAll() {
		return siteDao.findAll();
	}
	
	public Site findOneWithComments(int id) {
		Site site = siteDao.findOne(id);
		return site;
	}

}
