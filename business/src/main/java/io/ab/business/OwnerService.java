package io.ab.business;

import javax.servlet.ServletContext;

import io.ab.business.dto.AddTopoForm;
import io.ab.business.dto.SignInForm;
import io.ab.business.dto.SignUpForm;
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

	public void signIn(SignInForm signInForm) {
		this.error = null;

		if (signInForm.hasNullOrEmpty()) {
			error = "Renseignez l'email et le mot de passe.";
			return;
		}

		if (!this.ownerDao.exists(signInForm.getEmail())) {
			error = "Il n'existe aucun propriétaire de topos avec cette email.";
			return;
		}

		if (!this.ownerDao.checkPassword(signInForm.getEmail(), signInForm.getPassword())) {
			error = "Mot de passe invalide";
			return;
		}

	}

	public int signUp(SignUpForm signUpForm) {
		this.error = null;

		if (signUpForm.hasNullOrEmpty()) {
			this.error = "Erreur: champ manquant";
			return -1;
		}

		if (!signUpForm.passwordIsMatching()) {
			error = "Les deux mots de passe ne correspondent pas";
			return -1;
		}

		if (this.ownerDao.exists(signUpForm.getEmail())) {
			error = "Un compte avec cette email existe déjà.";
			return -1;
		}

		return this.ownerDao.createOne(signUpForm.getFirstName(), signUpForm.getLastName(),
				signUpForm.getEmail(), signUpForm.getPassword());

	}

	public void addTopo(AddTopoForm addTopoForm, Owner owner) {
		if (addTopoForm.getTopoId() != null) {
			this.ownerDao.addTopo(addTopoForm.getTopoId(), owner.getId());
			return;
		}

		if (addTopoForm.getSiteId() != null && addTopoForm.getTopoName() != null) {
			int topoId = this.topoService.createOne(addTopoForm.getTopoName(), addTopoForm.getSiteId());
			this.ownerDao.addTopo(topoId, owner.getId());
			return;
		}

		if (addTopoForm.getSiteName() != null && addTopoForm.getTopoName() != null) {
			String siteName = addTopoForm.getSiteName();
			String topoName = addTopoForm.getTopoName();
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
