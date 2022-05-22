package acme.features.inventor.quantity;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor,Quantity>{
	@Autowired
	InventorQuantityRepository repository;
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		boolean result;
		Toolkit toolkit;
		final int quantityId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitByQuantityId(quantityId);
		result = !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final int itemId;
		Item item;
		itemId = request.getModel().getInteger("item.id");
		item = this.repository.findItemById(itemId);
		entity.setItem(item);
		request.bind(entity, errors, "amount", "item.id", "toolkit.title");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "amount", "item.name", "toolkit.title");
		
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("published", entity.getToolkit().isPublished());
		final Collection<Item> items;
		final Collection<Item> publishedItems = new HashSet<>();
		items = this.repository.findAllItems();
		for(final Item i:items) {
			if(i.isPublished()) {
				publishedItems.add(i);
			}
		}
		model.setAttribute("publishedItems", publishedItems);
		
		
		
	}
		
	

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		int quantityId;
		Quantity quantity;
		quantityId = request.getModel().getInteger("id");
		quantity = this.repository.findQuantityById(quantityId);
		return quantity;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		
		assert entity != null;
		
		assert errors != null;
		
		if(entity.getItem().getItemType().toString().equals("TOOL")) {
			errors.state(request,entity.getAmount()<=1 && entity.getAmount()>=0 , "amount", "inventor.quantity.form.error.amount");
		}

	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
		
	}

}
