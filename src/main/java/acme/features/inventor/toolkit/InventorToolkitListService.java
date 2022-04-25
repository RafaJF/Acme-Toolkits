package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor, Toolkit> {
	
	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "totalPrice");
	}
	
	

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		final int inventorId = request.getPrincipal().getActiveRoleId();

		final Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(inventorId);
		
		final Collection<Toolkit> result = new HashSet<>();
		for(final Toolkit t : toolkits) {
			t.setTotalPrice(this.getTotalPrice(t));
			if(t.isPublished()) {
				result.add(t);
			}
		}
		
		return result;
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
