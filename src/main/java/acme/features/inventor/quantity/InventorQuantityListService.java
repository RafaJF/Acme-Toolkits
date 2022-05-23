	package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;
@Service
public class InventorQuantityListService implements AbstractListService<Inventor,Quantity>{
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	@Autowired
	protected InventorQuantityRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		
		assert request != null;
		int toolkitId;
		boolean result;
		toolkitId = request.getModel().getInteger("id");
		final Toolkit toolkit = this.toolkitRepository.findToolkitById(toolkitId);
		result = toolkit != null && (toolkit.isPublished() || request.isPrincipal(toolkit.getInventor()));
		return result;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		int toolkitId ;
		Collection<Quantity> result;
		toolkitId = request.getModel().getInteger("id");
		result = this.repository.findQuantitiesByToolkitId(toolkitId);
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newRetailPrice = this.moneyExchangeQuantity(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		request.unbind(entity, model, "amount", "item.code","item.name", "item.retailPrice", "item.itemType");
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		model.setAttribute("toolkitId", toolkitId);
		model.setAttribute("isPublished", entity.getToolkit().isPublished());
	}
	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert model != null;
		
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		model.setAttribute("toolkitId", toolkitId);
		Toolkit toolkit;
		toolkit = this.toolkitRepository.findToolkitById(toolkitId);
		model.setAttribute("isPublished", toolkit.isPublished());
	}

	//Métodos auxiliares
	
	public Money moneyExchangeQuantity(final Quantity q) {
		final String itemCurrency = q.getItem().getRetailPrice().getCurrency();
			
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
				
		if(!systemCurrency.equals(itemCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(q.getItem().getRetailPrice(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = q.getItem().getRetailPrice().getAmount();
		}
			
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
			
		return newBudget;
	}
}
