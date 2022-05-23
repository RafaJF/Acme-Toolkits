package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor,Quantity>{
	@Autowired
	protected InventorQuantityRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		boolean result;
		int quantityId;
		Toolkit toolkit;
		quantityId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitByQuantityId(quantityId);
		result = toolkit != null && (request.isPrincipal(toolkit.getInventor()) || toolkit.isPublished());
		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		int quantityId;
		Quantity quantity;
		quantityId = request.getModel().getInteger("id");
		quantity = this.repository.findQuantityById(quantityId);
		final Money newRetailPrice = this.moneyExchangeItems(quantity.getItem());
		quantity.getItem().setRetailPrice(newRetailPrice);
		return quantity;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount", "toolkit.title", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice",
			"item.itemType", "item.published");
		model.setAttribute("published", entity.getToolkit().isPublished());
		
	}
	
	//Métodos auxiliares
	
	public Money moneyExchangeItems(final Item i) {
		final String itemCurrency = i.getRetailPrice().getCurrency();
			
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
				
		if(!systemCurrency.equals(itemCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(i.getRetailPrice(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = i.getRetailPrice().getAmount();
		}
			
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
			
		return newBudget;
	}
	
}
