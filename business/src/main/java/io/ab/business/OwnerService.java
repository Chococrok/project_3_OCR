package io.ab.business;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import io.ab.consumer.DaoFactory;
import io.ab.consumer.OwnerDao;
import io.ab.model.Owner;

public class OwnerService {

	private OwnerDao ownerDao;
	private TopoService topoService;
	private SiteService siteService;
	private String error;

	public OwnerService(ServletContext context) {
		this.ownerDao = ((DaoFactory) context.getAttribute(DaoFactory.ATT_DAO_FACTORY)).getOwnerDao();
		this.topoService = new TopoService(context);
		this.siteService = new SiteService(context);
	}

	public Owner findOneByEmail(String email) {
		return this.ownerDao.findOneByEmail(email);
	}

	public Owner findOneById(int id) {
		return this.ownerDao.findOneById(id);
	}

	public void updateEmail(String email, int id) {
		this.ownerDao.updateEmail(email, id);
	}

	public void updatePhone(String phone, int id) {
		this.ownerDao.updatePhone(phone, id);
	}

	public void signIn(HttpServletRequest request) {
		this.error = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email.isEmpty() || password.isEmpty()) {
			error = "Renseignez l'email et le mot de passe.";
			return;
		}

		if (!this.ownerDao.exists(email)) {
			error = "Il n'existe aucun propriétaire de topos avec cette email.";
			return;
		}

		if (!this.ownerDao.checkPassword(email, password)) {
			error = "Mot de passe invalide";
			return;
		}

		request.getSession().setAttribute("owner", this.ownerDao.findOneByEmail(email));

	}

	public void signUp(HttpServletRequest request) {
		this.error = null;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()
				|| confirmPassword.isEmpty()) {
			this.error = "Erreur: champ manquant";
			return;
		}
		
		if (!password.equals(confirmPassword)) {
			error = "Les deux mots de passe ne correspondent pas";
			return;
		}

		if (this.ownerDao.exists(email)) {
			error = "Un compte avec cette email existe déjà.";
			return;
		}

		int ownerId = this.ownerDao.createOne(firstName, lastName, email, password);
		request.getSession().setAttribute("owner", this.ownerDao.findOneById(ownerId));

	}

	public void addTopo(HttpServletRequest request, Owner owner) {
		if (request.getParameter("topoId") != null) {
			int topoId = Integer.parseInt(request.getParameter("topoId"));
			this.ownerDao.addTopo(topoId, owner.getId());
			return;
		}

		if (request.getParameter("siteId") != null && request.getParameter("topoName") != null) {
			int siteId = Integer.parseInt(request.getParameter("siteId"));
			String topoName = request.getParameter("topoName");
			int topoId = this.topoService.createOne(topoName, siteId);
			this.ownerDao.addTopo(topoId, owner.getId());
			return;
		}

		if (request.getParameter("siteName") != null && request.getParameter("topoName") != null) {
			String siteName = request.getParameter("siteName");
			String topoName = request.getParameter("topoName");
			int topoId = this.topoService.createOne(topoName, this.siteService.createOne(siteName));
			this.ownerDao.addTopo(topoId, owner.getId());
			return;
		}

		error = "Erreur: champ manquant";
		return;
	}

	public boolean hasError() {
		return this.error != null;
	}

	public String getError() {
		return this.error;
	}

}
