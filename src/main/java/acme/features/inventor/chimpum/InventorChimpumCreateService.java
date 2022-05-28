package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor,Chimpum> {
	@Autowired
	InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int itemId;
		itemId = request.getModel().getInteger("itemName");
		final Item item = this.repository.findItemById(itemId);
		entity.setItem(item);
		
		request.bind(entity, errors, "code","title","startDate","endDate","link","description","budget");
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int inventorId;
		inventorId = request.getPrincipal().getActiveRoleId();
		final Collection<Item> items;
		items = this.repository.findAllItemsOfInventor(inventorId);
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "code","title","creationMoment","startDate","endDate","link","description","budget");
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		final Chimpum result;
		final Date now = Calendar.getInstance().getTime();
		result = new Chimpum();
		result.setCreationMoment(now);
		return result;
		
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("code")) {
		final boolean codeIsOk;
		boolean codeIsNotDuplicated;
		Chimpum existingChimpum;
		existingChimpum = this.repository.findChimpumByCode(entity.getCode());
		
	
		
		final String codeTrim =entity.getCode().substring(0,8);
		System.out.println(codeTrim);
		final String creationMoment = request.getModel().getAttribute("creationMoment").toString();
		final String creationMomentTrim = creationMoment.substring(2, 10).replace("/", "-");
		codeIsOk = creationMomentTrim.equals(codeTrim);
		codeIsNotDuplicated = existingChimpum == null;
		errors.state(request, codeIsOk, "code", "inventor.chimpum.form.error.format");
		errors.state(request, codeIsNotDuplicated, "code", "inventor.chimpum.form.error.duplicated");
		
		}
		
		if (!errors.hasErrors("budget")) {
			
			final String[] acceptedCurrencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
            boolean accepted = false;
            
            for(int i=0 ; i < acceptedCurrencies.length ; i++) {
            	
                if(entity.getBudget().getCurrency().equals(acceptedCurrencies[i].trim())) {
                    accepted = true;
                }
            }
			
			errors.state(request, accepted, "budget", "patron.patronage.form.error.budget-currency");
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "inventor.chimpum.form.error.budget-amount");
		}
		if(!errors.hasErrors("startDate")) {
			
			final Date startDateBorder = DateUtils.addMonths(entity.getCreationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(startDateBorder), "startDate", "inventor.chimpum.form.error.start-date");			
		}
		
		if(!errors.hasErrors("endDate")) {
			
			final Date endDateBorder = DateUtils.addWeeks(entity.getStartDate(), 1);
			final Date endDate = entity.getEndDate();
			
			errors.state(request, endDate.before(endDateBorder) , "endDate", "inventor.chimpum.form.error.end-date");
			
		}
		

		
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
		
	}

}
