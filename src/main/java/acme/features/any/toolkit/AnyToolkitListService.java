package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit> {
	
	@Autowired
	protected AnyToolkitRepository repository;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		result = this.repository.findAllToolkit();
		for(final Toolkit t: result) {
			t.setTotalPrice(this.getTotalPrice(t));
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
		result.setCurrency("EUR");
		result.setAmount(0.);
		for(final Quantity q:quantities) {
			
				final Money itemPrice = q.getItem().getRetailPrice();
				result.setAmount(result.getAmount()+ itemPrice.getAmount()*q.getAmount());
			
		}
		return result;
	}

	

}