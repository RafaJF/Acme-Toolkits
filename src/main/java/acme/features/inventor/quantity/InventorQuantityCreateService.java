package acme.features.inventor.quantity;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor,Quantity>{
	@Autowired
	protected InventorQuantityRepository repository;
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;
		int toolkitId;
		Toolkit toolkit;
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.toolkitRepository.findToolkitById(toolkitId);
		result = toolkit != null && !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
		return result;
		
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int itemId;
		Item item;
		itemId = request.getModel().getInteger("itemId");
		item = this.repository.findItemById(itemId);
		entity.setItem(item);
		request.bind(entity, errors, "amount", "toolkit.title");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "amount", "item.name", "toolkit.title");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("published", entity.getToolkit().isPublished());
		final Collection<Item> items ;
		final Collection<Item> publishedItems = new HashSet<>();
		
		items = this.repository.findAllItems();

		for(final Item i:items) {
			if(i.isPublished()) {
				final Quantity q = this.repository.findQuantityByItemIdAndToolkitId(i.getId(), entity.getToolkit().getId());
				if(q == null) {
					publishedItems.add(i);
				}
			}
		}
		model.setAttribute("publishedItems", publishedItems);
		
		
		
	}


	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		Quantity result;
		Item item;
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		Toolkit toolkit;
		toolkit = this.toolkitRepository.findToolkitById(toolkitId);
		result = new Quantity();
		item = new Item();
		result.setToolkit(toolkit);
		result.setItem(item);
		
		return result;
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
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
		
	}

}
