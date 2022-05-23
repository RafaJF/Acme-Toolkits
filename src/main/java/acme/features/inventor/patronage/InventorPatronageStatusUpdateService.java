package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

@Service
public class InventorPatronageStatusUpdateService implements AbstractUpdateService<Inventor,Patronage>{
	@Autowired
	InventorPatronageRepository repository;
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		return request.getPrincipal().getActiveRoleId() == patronage.getInventor().getId();
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		
		assert request != null;
		Patronage result;
		int patronageId;
		patronageId = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(patronageId);
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getCode(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "code", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("legalStuff")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getLegalStuff(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "legalStuff", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("moreInfo")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getMoreInfo(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "moreInfo", "alert-message.form.spam");
		}
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		
		assert request != null;
		assert entity != null;
		
		
		
		this.repository.save(entity);
		
	}
	

}
