package io.ab.climbing.consumer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Named
public class DaoFactory {

	@Inject
	private CommentDao commentDao;
	@Inject
	private LongueurDao longueurDao;
	@Inject
	private OwnerDao ownerDao;
	@Inject
	private SecteurDao secteurDao;
	@Inject
	private SiteDao siteDao;
	@Inject
	private TopoDao topoDao;
	@Inject
	private VoieDao voieDao;

	
	//<-- Getters
	public CommentDao getCommentDao() {
		return commentDao;
	}

	public LongueurDao getLongueurDao() {
		return longueurDao;
	}

	public OwnerDao getOwnerDao() {
		return ownerDao;
	}

	public SecteurDao getSecteurDao() {
		return secteurDao;
	}

	public SiteDao getSiteDao() {
		return siteDao;
	}

	public TopoDao getTopoDao() {
		return topoDao;
	}

	public VoieDao getVoieDao() {
		return voieDao;
	}
	//Getters -->
}