package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.features.inventor.chimpum.InventorChimpumRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorItemDeleteService implements AbstractDeleteService<Inventor, Item>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;
	@Autowired
	protected InventorChimpumRepository chimpumRepository;

	// AbstractCreateService<Inventor, Item> interface ---------------------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int itemId;
		final Item item;
		final Inventor inventor;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findOneById(itemId);
		inventor = item.getInventor();
		result = !item.isPublished() && request.isPrincipal(inventor);

		return result;
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}
	
	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		final Chimpum c = this.chimpumRepository.findOneChimpumByItemId(entity.getId());
		
		this.chimpumRepository.delete(c);
		this.repository.delete(entity);
	}

}
