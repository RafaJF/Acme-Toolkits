package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;
@Service
public class PatronPatronageShowService implements AbstractShowService<Patron,Patronage> {
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronageById(id);
		final int patronId = request.getPrincipal().getActiveRoleId();
		
		final boolean result = patronage.getPatron().getId() == patronId;
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final Patronage result = this.repository.findOnePatronageById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment","startDate","endDate","moreInfo","patron.company");
		model.setAttribute("patronId", entity.getPatron().getId());
		model.setAttribute("inventorId", entity.getInventor().getId());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getIdentity().getEmail());
		model.setAttribute("inventorInfo", entity.getInventor().getInfo());
		

		
	}

}
