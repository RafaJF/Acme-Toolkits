package acme.features.inventor.chimpum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository chimpumRepository;
	
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		boolean res;
		int id;
		Chimpum chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.chimpumRepository.findChimpumById(id);
		res = chimpum != null && chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		
		return res;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code","creationMoment","tittle","description","period","budget","link");
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code","creationMoment","tittle","description","period","budget","link");
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		
		assert request != null;
		
		Chimpum res;
		int id;
		id = request.getModel().getInteger("id");
		
		res = this.chimpumRepository.findChimpumById(id);
		
		return res;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("link") && (!entity.getLink().isEmpty())) {
			boolean isUrl;
			isUrl = (entity.getLink().startsWith("http") || entity.getLink().startsWith("www")) && entity.getLink().contains(".");
			errors.state(request, isUrl, "link", "inventor.chimpum.form.error.link");
		
		}
		if(!errors.hasErrors("budget")) {
			final Double amount = entity.getBudget().getAmount();
			
			final String[] acceptedCurrencies = this.chimpumRepository.findAcceptedCurrencies().split(",");
			final List<String> aceptedCurrenciesList = new ArrayList<String>();
			for(final String i: acceptedCurrencies) {
				aceptedCurrenciesList.add(i);
			}
			
			final boolean validCurrency = aceptedCurrenciesList.contains(entity.getBudget().getCurrency());
			
			errors.state(request, amount>0. , "budget", "inventor.chimpum.form.error.budget-amount-negative-or-zero");
			errors.state(request, validCurrency, "budget", "inventor.chimpum.form.error.budget-currency-invalid");
		}
		if(!errors.hasErrors("period")) {
			
			final Date period = entity.getPeriod();
			final Date creationMoment = entity.getCreationMoment();
			Date periodMin = DateUtils.addMonths(creationMoment, 1);
			periodMin = DateUtils.addWeeks(periodMin, 1);
			
			errors.state(request, period.after(periodMin), "period", "inventor.chimpum.form.error.period-must-be-after-dateOfCreation");
		}
		
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		this.chimpumRepository.save(entity);
		
		
	}

}
