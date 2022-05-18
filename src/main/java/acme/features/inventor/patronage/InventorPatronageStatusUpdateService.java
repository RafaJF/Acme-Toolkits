package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageStatusUpdateService implements AbstractUpdateService<Inventor,Patronage>{
	@Autowired
	InventorPatronageRepository repository;
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		return request.getPrincipal().getActiveRoleId() == patronage.getInventor().getId();
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		
		assert request != null;
		Patronage result;
		int patronageId;
		patronageId = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(patronageId);
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		
		assert request != null;
		assert entity != null;
		
		
		
		this.repository.save(entity);
		
	}
	

}
