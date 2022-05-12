package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		return true;
		
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
	
		request.unbind(entity, model, "amount", "item.name", "toolkit.title");
		
		
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		Quantity result;
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		Toolkit toolkit;
		toolkit = this.toolkitRepository.findToolkitById(toolkitId);
		result = new Quantity();
		result.setToolkit(toolkit);
		
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		
		assert entity != null;
		
		assert errors != null;
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
