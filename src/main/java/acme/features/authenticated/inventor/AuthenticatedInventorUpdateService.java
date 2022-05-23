package acme.features.authenticated.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

@Service
public class AuthenticatedInventorUpdateService implements AbstractUpdateService<Authenticated, Inventor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedInventorRepository repository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------


	@Override
	public boolean authorise(final Request<Inventor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Inventor> request, final Inventor entity, final Errors errors) {
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
	public void bind(final Request<Inventor> request, final Inventor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "statement", "info");
	}

	@Override
	public void unbind(final Request<Inventor> request, final Inventor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "statement", "info");
	}

	@Override
	public Inventor findOne(final Request<Inventor> request) {
		assert request != null;

		Inventor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneInventorByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Inventor> request, final Inventor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Inventor> request, final Response<Inventor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
