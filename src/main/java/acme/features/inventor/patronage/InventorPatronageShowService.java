package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		final int patronageInventorId = this.repository.findOnePatronageById(patronageId).getInventor().getId();

		return  inventorId == patronageInventorId; 
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		model.setAttribute("company", entity.getPatron().getCompany());
	}
	
	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOnePatronageById(id);
	}

}