package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.quantity.InventorQuantityRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;
@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor,Toolkit> {
	
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	@Autowired
	protected InventorQuantityRepository quantityRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		int toolkitId;
		Toolkit toolkit;
		Inventor inventor;
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.toolkitRepository.findToolkitById(toolkitId);
		inventor = toolkit.getInventor();
		return request.isPrincipal(inventor) && !toolkit.isPublished(); 
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assamblyNotes", "url","published");
		
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
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		result = this.toolkitRepository.findToolkitById(toolkitId);
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		Collection<Quantity> quantities;
		quantities = this.toolkitRepository.findAllQuantityOfToolkit(entity);
		for (final Quantity q : quantities) {
			this.quantityRepository.delete(q);
		}
		this.toolkitRepository.delete(entity);
		
	}

}
