package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor,Toolkit> {
	@Autowired
	InventorToolkitRepository repository;
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		int toolkitId;
		Toolkit toolkit;
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);
	
		return !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assamblyNotes", "url","published");
		final Collection<Quantity> quantities = this.repository.findAllQuantityOfToolkit(entity);
		errors.state(request, !quantities.isEmpty() , "published", "inventor.toolkit.form.error.published");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assamblyNotes", "url","published");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		final int toolkitId = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(toolkitId);
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		entity.setPublished(true);
		
		this.repository.save(entity);
		
	}
	

}
