package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor,Toolkit> {
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
		result = this.repository.findToolkitById(toolkitId);
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("url")) {
			boolean isUrl;
			isUrl = (entity.getUrl().startsWith("http") || entity.getUrl().startsWith("www")) && entity.getUrl().contains(".");
			errors.state(request, isUrl, "url", "inventor.toolkit.form.error.url");
		}
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
