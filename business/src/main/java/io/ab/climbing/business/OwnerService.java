package io.ab.climbing.business;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import io.ab.climbing.business.dto.AddTopoForm;
import io.ab.climbing.business.dto.SignInForm;
import io.ab.climbing.business.dto.SignUpForm;
import io.ab.climbing.consumer.DaoFactory;
import io.ab.climbing.consumer.OwnerDao;
import io.ab.climbing.model.Owner;

@Named
public class OwnerService extends AbstractService {

	@Inject
	private TopoService topoService;
	@Inject
	private SiteService siteService;
	
	private String error;

	public Owner findOneByEmail(String email) {
		return this.daoFactory.getOwnerDao().findOneByEmail(email);
	}

	public Owner findOneById(int id) {
		return this.daoFactory.getOwnerDao().findOneById(id);
	}

	public void updateEmail(String email, int id) {
		this.daoFactory.getOwnerDao().updateEmail(email, id);
	}

	public void updatePhone(String phone, int id) {
		this.daoFactory.getOwnerDao().updatePhone(phone, id);
	}

	public void signIn(SignInForm signInForm) {
		this.error = null;

		if (signInForm.hasNullOrEmpty()) {
			error = "Renseignez l'email et le mot de passe.";
			return;
		}

		if (!this.daoFactory.getOwnerDao().exists(signInForm.getEmail())) {
			error = "Il n'existe aucun propriétaire de topos avec cette email.";
			return;
		}

		if (!this.daoFactory.getOwnerDao().checkPassword(signInForm.getEmail(), signInForm.getPassword())) {
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

		if (this.daoFactory.getOwnerDao().exists(signUpForm.getEmail())) {
			error = "Un compte avec cette email existe déjà.";
			return -1;
		}

		return this.daoFactory.getOwnerDao().createOne(signUpForm.getFirstName(), signUpForm.getLastName(), signUpForm.getEmail(),
				signUpForm.getPassword());

	}

	public void addTopo(AddTopoForm addTopoForm, Owner owner) {
		if (addTopoForm.getTopoId() != null) {
			this.daoFactory.getOwnerDao().addTopo(addTopoForm.getTopoId(), owner.getId());
			return;
		}

		if (addTopoForm.getSiteId() != null && addTopoForm.getTopoName() != null) {
			int topoId = this.topoService.createOne(addTopoForm.getTopoName(), addTopoForm.getSiteId());
			this.daoFactory.getOwnerDao().addTopo(topoId, owner.getId());
			return;
		}

		if (addTopoForm.getSiteName() != null && addTopoForm.getTopoName() != null) {
			String siteName = addTopoForm.getSiteName();
			String topoName = addTopoForm.getTopoName();
			int topoId = this.topoService.createOne(topoName, this.siteService.addEmptyOne(siteName));
			this.daoFactory.getOwnerDao().addTopo(topoId, owner.getId());
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

	public void updateOne(Owner owner) {
		this.daoFactory.getOwnerDao().updateOne(owner);
	}

	public void deleteOne(Integer id) {
		this.daoFactory.getOwnerDao().deleteTopos(id);
		this.daoFactory.getOwnerDao().deleteOne(id);
		
	}

}
