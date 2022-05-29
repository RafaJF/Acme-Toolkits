package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum>{
	
	@Autowired
	protected InventorChimpumRepository chimpumRepository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Chimpum> findMany(final Request<Chimpum> request) {
		
		assert request != null;
		
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final Collection<Item> items = this.chimpumRepository.findItemByInventorId(inventorId);
		final Collection<Chimpum> chimpums = new HashSet<Chimpum>();
		for(final Item i: items) {
			final Chimpum chimpum = this.chimpumRepository.findChimpumByItemId(i.getId());
			if(chimpum != null) {
				chimpums.add(chimpum);
			}
			
		}
		return chimpums;
		
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "code", "tittle", "budget","item.code");
		
	}

	

}
