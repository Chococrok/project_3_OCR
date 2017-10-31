package io.ab.business;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.OwnerDao;
import io.ab.model.Owner;

public class OwnerService {
	
	private OwnerDao ownerDao;
	private String error;

	public OwnerService(ServletContext context) {
		this.ownerDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getOwnerDao();
	}
	
	public Owner findOneByEmail(String email) {
		return this.ownerDao.findOneByEmail(email);
	}
	
	public void signInOwner(HttpServletRequest request) {
		this.error = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(!this.ownerDao.exists(email)) {
			error = "Il n'existe aucun propriétaire de topos avec cette email.";
			return;
		}
		
		if(!this.ownerDao.checkPassword(email, password)) {
			error = "Mot de passe invalide";
			return;
		}
		
		request.getSession().setAttribute("owner", this.ownerDao.findOneByEmail(email));
		
	}
	
	public boolean hasError() {
		return this.error != null;
	}
	
	public String getError() {
		return this.error;
	}

}
