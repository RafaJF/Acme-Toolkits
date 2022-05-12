	package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;
@Service
public class InventorQuantityListService implements AbstractListService<Inventor,Quantity>{
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	@Autowired
	protected InventorQuantityRepository repository;
	@Override
	public boolean authorise(final Request<Quantity> request) {
		
		assert request != null;
		int masterId;
		masterId = request.getModel().getInteger("masterId");
		final Toolkit toolkit = this.toolkitRepository.findToolkitById(masterId);
		return toolkit != null;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		int masterId ;
		Collection<Quantity> result;
		masterId = request.getModel().getInteger("masterId");
		result = this.repository.findQuantitiesByToolkitId(masterId);
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int masterId;
		masterId = request.getModel().getInteger("masterId");
		request.unbind(entity, model, "amount", "item.name");
		model.setAttribute("masterId", masterId);
	}

}
