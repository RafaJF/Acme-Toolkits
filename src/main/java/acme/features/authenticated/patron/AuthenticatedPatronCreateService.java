package acme.features.authenticated.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;
import spamDetector.SpamDetector;

@Service
public class AuthenticatedPatronCreateService implements AbstractCreateService<Authenticated, Patron> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedPatronRepository repository;

	// AbstractCreateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Patron> request) {
		assert request != null;

		boolean result;
		
		result = !request.getPrincipal().hasRole(Patron.class); 

		return result;
	}

	@Override
	public void bind(final Request<Patron> request, final Patron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "statement", "info");
	}

	@Override
	public void unbind(final Request<Patron> request, final Patron entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "statement", "info");
	}

	@Override
	public Patron instantiate(final Request<Patron> request) {
		assert request != null;

		Patron result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Patron();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Patron> request, final Patron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("company")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getCompany(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "company", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("statement")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getStatement(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "statement", "alert-message.form.spam");
		}
	}

	@Override
	public void create(final Request<Patron> request, final Patron entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Patron> request, final Response<Patron> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
