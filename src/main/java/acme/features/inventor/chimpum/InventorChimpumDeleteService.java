package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService  implements AbstractDeleteService<Inventor,Chimpum>  {

	@Autowired
	protected InventorChimpumRepository repository;
	@Autowired
	protected InventorItemRepository itemRepository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		request.bind(entity, errors, "code", "title", "description", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		request.unbind(entity, model, "code", "title", "description", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		Chimpum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);

		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		Item item;
		
		item = this.repository.findOneItemByChimpumId(entity.getId());
		item.setChimpum(null);
		this.itemRepository.save(item);
		
		this.repository.delete(entity);
	}
}
