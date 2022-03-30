package acme.entities.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class ItemsShowService implements AbstractShowService<Administrator, Item> {
	
	@Autowired
	protected AdministratorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request !=null;
		return true;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		Item res;
		res = (Item) this.repository.findAllItemPublished();
		return res;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"name", "code","technology","description","retailPrice","info","itemType");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		
	}

}
