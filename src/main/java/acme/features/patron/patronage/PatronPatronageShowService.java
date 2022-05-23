package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.patronage.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;
@Service
public class PatronPatronageShowService implements AbstractShowService<Patron,Patronage> {
	
	@Autowired
	protected PatronPatronageRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findPatronageById(id);
		final int patronId = request.getPrincipal().getActiveRoleId();
		
		return patronage.getPatron().getId() == patronId;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Patronage p = this.repository.findPatronageById(id);
		
		
		
		return p;	
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newBudget = this.moneyExchangePatronages(entity);
		model.setAttribute("newBudget", newBudget);
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment","startDate","endDate","moreInfo","patron.company","published");
		model.setAttribute("patronId", entity.getPatron().getId());
		model.setAttribute("inventorId", entity.getInventor().getId());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getIdentity().getEmail());
		model.setAttribute("inventorInfo", entity.getInventor().getInfo());
		

		
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
