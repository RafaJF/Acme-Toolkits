package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import spamDetector.SpamDetector;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;

	// AbstracCreateService<Any, Chirp> interface --------------

	@Override
	public boolean authorise(final Request<Chirp> request) {
		
		assert request != null;

		return true;
	}
	
	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		
		Chirp result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Chirp();
		result.setCreationMoment(moment);
		result.setTitle("");
		result.setAutor("");
		result.setBody("");
		result.setEmail("");
		
		return result;

	}
	
	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "autor", "body", "email");
	}


	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		
		if(!errors.hasErrors("title")) {
			errors.state(request, entity.getTitle().length()<100, "title", "any.chirp.form.error.title");
		}
		
		if(!errors.hasErrors("autor")) {
			errors.state(request, entity.getAutor().length()<100, "autor", "any.chirp.form.error.autor");		
		}
		
		if(!errors.hasErrors("body")) {
			errors.state(request, entity.getBody().length()<256, "body", "any.chirp.form.error.body");
		}
		
		if(!errors.hasErrors("title")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getTitle(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "title", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("autor")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getAutor(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "autor", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("body")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getBody(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "body", "alert-message.form.spam");
		}
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}
	
	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "autor", "body", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("creationMoment", entity.getCreationMoment());
	}
	
	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
