package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor,Chimpum>{
	@Autowired
	InventorChimpumRepository repository;
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		int chimpumId;
		Inventor inventor;
		chimpumId = request.getModel().getInteger("id");
		final Chimpum chimpum = this.repository.findChimpumById(chimpumId);
		inventor = chimpum.getItem().getInventor();
		return request.isPrincipal(inventor);
	
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors, "code","title","startDate","endDate","link","description","budget");
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		int inventorId;
		inventorId = request.getPrincipal().getActiveRoleId();
		final Collection<Item> items;
		items = this.repository.findAllItemsOfInventor(inventorId);
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "code","title","creationMoment","startDate","endDate","link","description","budget");
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		final Chimpum result;
		int chimpumId;
		chimpumId = request.getModel().getInteger("id");
		result = this.repository.findChimpumById(chimpumId);
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity!= null;
		this.repository.delete(entity);
		
	}

}
