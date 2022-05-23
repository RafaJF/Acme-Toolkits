package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.patronage.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;
@Service
public class PatronPatronageListService implements AbstractListService<Patron,Patronage> {
	@Autowired
	protected PatronPatronageRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;
		final Integer id = request.getPrincipal().getActiveRoleId();
		Collection<Patronage> result;
		result = this.repository.findPatronages(id);
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newBudget = this.moneyExchangePatronages(entity);
		model.setAttribute("newBudget", newBudget);
		
		if(entity.isPublished()) {
			model.setAttribute("published", "\u2714");
		} else if(!entity.isPublished()) {
			model.setAttribute("published", "\u274C");
		}
		
		request.unbind(entity, model, "status", "code", "budget");
		model.setAttribute("inventor", entity.getInventor().getUserAccount().getUsername());
	}
	
	//Método auxiliar cambio de divisa
	public Money moneyExchangePatronages(final Patronage p) {
		final String itemCurrency = p.getBudget().getCurrency();
	
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
		
		if(!systemCurrency.equals(itemCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(p.getBudget(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = p.getBudget().getAmount();
		}
		
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
		
		return newBudget;
	}

}
