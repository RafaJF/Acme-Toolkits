package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		final int inventorId = request.getPrincipal().getActiveRoleId();

		final Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(inventorId);

		final Toolkit requested = this.findOne(request);

		return toolkits.contains(requested);
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		
		final Toolkit toolkit = this.repository.findToolkitById(id);
		
		toolkit.setTotalPrice(this.getTotalPrice(toolkit));
		
		return toolkit;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assamblyNotes", "url", "totalPrice","published");
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