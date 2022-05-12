package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor,Quantity>{
	@Autowired
	protected InventorQuantityRepository repository;
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		return true;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		final int quantityId = request.getModel().getInteger("id");
		final Quantity quantity = this.repository.findQuantityById(quantityId);
		return quantity;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount", "toolkit.code", "item.code");
		
		
	}

}
