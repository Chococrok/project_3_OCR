package io.ab.climbing.model;

import java.sql.Timestamp;

public class Comment {
	
	public static final String TOPO_ID = "topo_id";
	public static final String SITE_ID = "site_id";
	public static final String VOIE_ID = "voie_id";
	public static final String SECTEUR_ID = "secteur_id";
	public static final String LONGUEURE_ID = "longueure_id";

	private Integer id;
	
	private String content;
	
	private Timestamp timestamp;

	private Longueur longueur;

	private Secteur secteur;

	private Site site;

	private Topo topo;

	private Voie voie;

	public Comment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp date) {
		this.timestamp = date;
	}

	public Longueur getLongueur() {
		return this.longueur;
	}

	public void setLongueur(Longueur longueur) {
		this.longueur = longueur;
	}

	public Secteur getSecteur() {
		return this.secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Topo getTopo() {
		return this.topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Voie getVoie() {
		return this.voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

}