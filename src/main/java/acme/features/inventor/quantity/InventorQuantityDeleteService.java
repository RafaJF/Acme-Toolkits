package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;
@Service
public class InventorQuantityDeleteService implements AbstractDeleteService<Inventor,Quantity>{
	@Autowired
	InventorQuantityRepository repository;
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		boolean result;
		int quantityId;
		quantityId = request.getModel().getInteger("id");
		final Toolkit toolkit;
		toolkit = this.repository.findToolkitByQuantityId(quantityId);
		result = !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "amount", "item.name", "toolkit.title");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "amount", "item.name", "toolkit.title","toolkit.published");
		model.setAttribute("published", entity.getToolkit().isPublished());
		
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
		
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request !=null;
		assert entity != null;
		this.repository.delete(entity);
		
	}

}
