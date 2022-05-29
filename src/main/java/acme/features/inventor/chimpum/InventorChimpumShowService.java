package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum> {
	
	@Autowired
	protected InventorChimpumRepository chimpumRepository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean res;
		int id;
		Chimpum chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.chimpumRepository.findChimpumById(id);
		res = chimpum != null && chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		
		return res;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.chimpumRepository.findChimpumById(id);

		return res;
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code","creationMoment","tittle","description","period","budget","link");
	}

}
