package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.moneyExchange.MoneyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolListService implements AbstractListService<Inventor,Item>{
	
	// Internal state --------------------------------------------------------- 
	 
	@Autowired 
	protected InventorItemRepository repository; 
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	 
	@Override 
	public boolean authorise(final Request<Item> request) { 
		
		assert request != null; 
		return true; 
	} 
 
	@Override 
	public Collection<Item> findMany(final Request<Item> request) { 
		
		assert request != null; 
 
		Collection<Item> result; 
		final int inventorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findToolsByInventor(inventorId); 
 
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<Item> request, final Item entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		final Money newRetailPrice = this.moneyExchangeItem(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		if(entity.isPublished()) {
			model.setAttribute("published", "\u2714");
		} else if(!entity.isPublished()) {
			model.setAttribute("published", "\u274C");
		}
		
		request.unbind(entity, model, "name","code","retailPrice"); 
		 
	} 
	
	//Método auxiliar cambio de divisa
		public Money moneyExchangeItem(final Item i) {

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
