package acme.features.any.toolkit;

import java.util.Collection;
import java.util.HashSet;

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
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit> {
	
	@Autowired
	protected AnyToolkitRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> toolkits;
		final Collection<Toolkit> result = new HashSet<>();
		toolkits = this.repository.findAllToolkit();
		for(final Toolkit t: toolkits) {
			if(t.isPublished()) {
			t.setTotalPrice(this.getTotalPrice(t));
			result.add(t);
		}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title","totalPrice");

	}

	private Money getTotalPrice(final Toolkit t) {
        final Collection<Quantity> quantities = this.repository.findAllQuantityOfToolkit(t);
        final Money result = new Money();
        final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
        result.setCurrency(systemCurrency);
        result.setAmount(0.);
        for(final Quantity q:quantities) {

                final String itemCurrency = q.getItem().getRetailPrice().getCurrency();
                if (!itemCurrency.equals(systemCurrency)) {
                	final Money newRetailPrice = this.moneyExchangeItems(q.getItem());
            		q.getItem().setRetailPrice(newRetailPrice);
                }
                final Money itemPrice = q.getItem().getRetailPrice();
                result.setAmount(result.getAmount()+ itemPrice.getAmount()*q.getAmount());

        }
        return result;
    }
	
	//Método auxiliar cambio de divisa
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