package acme.features.inventor.item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractCreateService<Inventor, Item> interface ---------------------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		
		final Inventor inventor;
		final Item result;
		
		inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		result = new Item();
		result.setInventor(inventor);
		result.setName("");
		result.setCode("");
		result.setTechnology("");
		result.setDescription("");
		result.setInfo("");
		result.setPublished(false);
		
		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "info", "itemType");
		
	}
	
	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			
			Item existing;
			
			existing = this.repository.findItemByCode(entity.getCode());
			
			errors.state(request, existing == null, "code", "inventor.item.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("retailPrice")) {
			final Double amount = entity.getRetailPrice().getAmount();
			
			final String[] acceptedCurrencies = this.repository.findAcceptedCurrencies().split(",");
			final Set<String> acceptedCurrenciesS = new HashSet<String>();
			Collections.addAll(acceptedCurrenciesS, acceptedCurrencies);
			final Boolean validCurrency = acceptedCurrenciesS.contains(entity.getRetailPrice().getCurrency());
			
			errors.state(request, amount > 0., "retailPrice", "inventor.item.form.error.retail-price-amount-negative-or-zero");
			errors.state(request, validCurrency, "retailPrice", "inventor.item.form.error.retail-price-currency-invalid");
		}
		
		if(!errors.hasErrors("name")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getName(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "name", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("technology")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getTechnology(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "technology", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("description")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getDescription(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "description", "alert-message.form.spam");
		}
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "itemType");
		
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);		
	}

}
