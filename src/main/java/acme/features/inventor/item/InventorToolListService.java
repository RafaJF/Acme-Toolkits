package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolListService implements AbstractListService<Inventor,Item>{
	
	// Internal state --------------------------------------------------------- 
	 
	@Autowired 
	protected InventorItemRepository repository; 
	 
	@Override 
	public boolean authorise(final Request<Item> request) { 
		
		assert request != null; 
		return true; 
	} 
 
	@Override 
	public Collection<Item> findMany(final Request<Item> request) { 
		
		assert request != null; 
 
		Collection<Item> result; 
		final int inventorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findToolsByInventor(inventorId); 
 
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<Item> request, final Item entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		if(entity.isPublished()) {
			model.setAttribute("published", "\u2714");
		} else if(!entity.isPublished()) {
			model.setAttribute("published", "\u274C");
		}
		
		request.unbind(entity, model, "name","code","retailPrice"); 
		 
	} 

}
