package acme.features.inventor.item;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemListService implements AbstractListService<Inventor,Item> {
	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		
		assert request !=null;
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		final Toolkit t = this.inventorToolkitRepository.findToolkitById(toolkitId);
		return t.isPublished();
			
	}
	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		
		final Collection<Item> result = new HashSet<>();
		int toolkitId;
		toolkitId= request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.inventorToolkitRepository.findQuantitiesByToolkitId(toolkitId);
		for(final Quantity q: quantities) {
			final int quantityId = q.getId();
			final Item i = this.inventorToolkitRepository.findItemByQuantityId(quantityId);
			if(i.isPublished()) {
				result.add(i);
			}
		}
		return result;
	}

		
		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null; 
			assert entity != null; 
			assert model != null; 
			request.unbind(entity, model, "name", "code", "retailPrice");
		}
		
	

	

}
