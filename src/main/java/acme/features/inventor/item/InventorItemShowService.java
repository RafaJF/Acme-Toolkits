package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.moneyExchange.MoneyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.features.inventor.chimpum.InventorChimpumRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	@Autowired
	protected InventorChimpumRepository chimpumRepository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int itemId = request.getModel().getInteger("id");
		final int itemInventorId = this.repository.findOneById(itemId).getInventor().getId();

		return  inventorId == itemInventorId;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newRetailPrice = this.moneyExchangePatronages(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		model.setAttribute("itemId", entity.getId());
		final Chimpum c = entity.getChimpum();
		if(c!=null) {
			model.setAttribute("chimpum", c.getId());
		}
		
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "itemType", "published");
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

//		final int id = request.getModel().getInteger("id");
//		final Item i = this.repository.findOneById(id);
//		
//		final Money newRetailPrice = this.moneyExchangePatronages(i);
//		i.setRetailPrice(newRetailPrice);
		
//		return i;
	}
	
	//Método auxiliar cambio de divisa
	public Money moneyExchangePatronages(final Item i) {
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
