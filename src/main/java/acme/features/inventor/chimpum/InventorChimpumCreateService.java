package acme.features.inventor.chimpum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

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
		final Item item;
		itemId = request.getModel().getInteger("item");
		item = this.repository.findItemById(itemId);
		entity.setItem(item);
		final Date now = Calendar.getInstance().getTime();
		final SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		final String creationMoment = format.format(now);
		entity.setCode(creationMoment);

		
		request.bind(entity, errors,"title","startDate","endDate","link","description","budget");
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int inventorId;
		inventorId = request.getPrincipal().getActiveRoleId();
		final Collection<Item> items;
		final Collection<Item> myPublishedItems= new HashSet<>();
		final Collection<Item> assignedItems = this.repository.findAllAsignedItems();
		items = this.repository.findAllItemsOfInventor(inventorId);
		for(final Item i:items) {
			if(i.isPublished() && !assignedItems.contains(i)) {
				myPublishedItems.add(i);
			}
		}
		model.setAttribute("items", myPublishedItems);
		
		
		request.unbind(entity, model,"title","creationMoment","startDate","endDate","link","description","budget");
		
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
//		if(!errors.hasErrors("code")) {
//		final boolean codeIsOk;
//		boolean codeIsNotDuplicated;
//		Chimpum existingChimpum;
//		existingChimpum = this.repository.findChimpumByCode(entity.getCode());
//		
//	
//		final Date now = Calendar.getInstance().getTime();
//		final String codeTrim =entity.getCode().substring(0,8);
//		final SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
//		final String creationMoment = format.format(now);
//		
//		codeIsOk = creationMoment.equals(codeTrim);
//		codeIsNotDuplicated = existingChimpum == null;
//		errors.state(request, codeIsOk, "code", "inventor.chimpum.form.error.format");
//		errors.state(request, codeIsNotDuplicated, "code", "inventor.chimpum.form.error.duplicated");
//		
//		}
		
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
		
		if(!errors.hasErrors("endDate") && entity.getStartDate()!=null) {
			
			final Date endDateBorder = DateUtils.addWeeks(entity.getStartDate(), 1);
			final Date endDate = entity.getEndDate();
			
			errors.state(request, endDate.before(endDateBorder) , "endDate", "inventor.chimpum.form.error.end-date");
			
		}
		if(!errors.hasErrors("description")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getTitle(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "title", "alert-message.form.spam");
		}
		if(!errors.hasErrors("title")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getTitle(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "title", "alert-message.form.spam");
		}

		
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
		
	}

}
