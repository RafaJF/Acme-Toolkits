package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorComponentShowService implements AbstractShowService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorComponentRepository repository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int itemId = request.getModel().getInteger("id");
		final int quantityInventorId = this.repository.findOneQuantityByComponentId(itemId).getInventor().getId();

		return  inventorId == quantityInventorId; 
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info");
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneComponentById(id);
	}

}