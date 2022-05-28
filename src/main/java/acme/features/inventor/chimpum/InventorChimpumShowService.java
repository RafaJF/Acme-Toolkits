package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor,Chimpum>{
	
	@Autowired
	InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		return true;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		int chimpumId;
		chimpumId = request.getModel().getInteger("id");
		Chimpum result;
		result = this.repository.findChimpumById(chimpumId);
		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code","title","creationMoment","startDate","endDate","link","item.name","description","budget");
		int inventorId;
		inventorId = request.getPrincipal().getActiveRoleId();
		final Collection<Item> items;
		items = this.repository.findAllItemsOfInventor(inventorId);
		model.setAttribute("items", items);
		model.setAttribute("selectedName", entity.getItem().getName());
		model.setAttribute("selectedType", entity.getItem().getItemType());
		model.setAttribute("selectedId", entity.getItem().getId());
	}

}
