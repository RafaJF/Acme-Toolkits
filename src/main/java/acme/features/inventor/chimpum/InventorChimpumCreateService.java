package acme.features.inventor.chimpum;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{
	
	@Autowired
	protected InventorChimpumRepository repository;
	@Autowired
	protected InventorItemRepository itemRepository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		Item item;
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int itemId = request.getModel().getInteger("itemId");
		item = this.itemRepository.findOneById(itemId);
		final int itemInventorId = this.itemRepository.findOneById(itemId).getInventor().getId();

		return  inventorId == itemInventorId && item.isPublished(); 
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		request.bind(entity, errors, "code", "title", "description", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		final LocalDate cm =  entity.getCreationMoment().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		entity.setCode(entity.getCode()+"-"+this.codeGenerator(cm));
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		request.unbind(entity, model, "title", "description", "budget", "creationMoment", "startDate", "endDate", "moreInfo");	
		model.setAttribute("itemId", request.getModel().getInteger("itemId"));
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
	
		result = new Chimpum();
		result.setCreationMoment(moment);
//		result.setCode(this.codeGenerator(moment));
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

        final SystemConfiguration systemConfig = this.systemConfigRepository.findSystemConfiguration();
        final String StrongEN = systemConfig.getStrongSpamTermsEn();
        final String StrongES = systemConfig.getStrongSpamTermsEs();
        final String WeakEN = systemConfig.getWeakSpamTermsEn();
        final String WeakES = systemConfig.getWeakSpamTermsEs();

        final double StrongThreshold = systemConfig.getStrongThreshold();
        final double WeakThreshold = systemConfig.getWeakThreshold();
        
        if(!errors.hasErrors("code")) {
        	
    		Chimpum existing;
    		existing = this.repository.findOneChimpumByCode(entity.getCode());
    		
    		if(existing!=null) {
    			errors.state(request, existing.getId()==entity.getId(), "code", "inventor.chimpum.form.error.duplicated-code");
    		}
    	}
		
        if(!errors.hasErrors("title")) {
            final boolean res;
            res = SpamDetector.spamDetector(entity.getTitle(),StrongEN,StrongES,WeakEN,WeakES,StrongThreshold,WeakThreshold);
            errors.state(request, res, "title", "alert-message.form.spam");
        }
        
        if(!errors.hasErrors("description")) {
            final boolean res;
            res = SpamDetector.spamDetector(entity.getDescription(),StrongEN,StrongES,WeakEN,WeakES,StrongThreshold,WeakThreshold);
            errors.state(request, res, "description", "alert-message.form.spam");
        }
        
        if(!errors.hasErrors("moreInfo")) {
            final boolean res;
            res = SpamDetector.spamDetector(entity.getMoreInfo(),StrongEN,StrongES,WeakEN,WeakES,StrongThreshold,WeakThreshold);
            errors.state(request, res, "moreInfo", "alert-message.form.spam");
        }
		
		if(!errors.hasErrors("budget")) {
			final List<String> currencies = new ArrayList<>();
			String currency;
			Double amount;
			
			for(final String c: this.systemConfigRepository.acceptedCurrencies().split(",")) {
				currencies.add(c.trim());
			}
			
			currency = entity.getBudget().getCurrency();
			
			amount = entity.getBudget().getAmount();
			
			errors.state(request, currencies.contains(currency) , "budget","inventor.chimpum.form.error.currency");
			errors.state(request, amount>=0.00 , "budget","inventor.chimpum.form.error.amount-negative");
		}
		
		if(entity.getStartDate()!=null) {
			if(!errors.hasErrors("startDate")) {
				Date startDate;
				startDate = entity.getStartDate();

				final long diff = startDate.getTime() - entity.getCreationMoment().getTime();
				final TimeUnit time = TimeUnit.DAYS; 
		        final long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		        
		        errors.state(request, diffrence>=30 , "startDate","inventor.chimpum.form.error.startDate");
			}
			
			if(!errors.hasErrors("endDate")) {
				Date endDate;
				endDate = entity.getEndDate();
				
				final long diff = endDate.getTime() - entity.getStartDate().getTime();
			    final TimeUnit time = TimeUnit.DAYS; 
			    final long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
			        
			    errors.state(request, diffrence>=7 , "endDate","inventor.chimpum.form.error.endDate");
			}
		}
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		Item item;
		
		this.repository.save(entity);
		final int itemId = request.getModel().getInteger("itemId");
		item = this.itemRepository.findOneById(itemId);
		item.setChimpum(entity);
		this.itemRepository.save(item);
	}
	
	//Método auxiliar que genera automáticamente el código
	
	public String codeGenerator(final LocalDate creationMoment) {
		String result = "";
		
		final Integer day = creationMoment.getDayOfMonth();
		final Integer month = creationMoment.getMonthValue();
		final Integer year = creationMoment.getYear();

		final String yearCode = year.toString().substring(2, 4);
		String monthCode= "";
		String dayCode= "";
		
		if(month.toString().length()==1) {
			monthCode = "0" + month.toString();
		}else{
			monthCode = month.toString();
		}
			
		if(day.toString().length()==1) {
			dayCode = "0" + day.toString();
		}else {
			dayCode = day.toString();
		}
				
		result = yearCode + "-" + monthCode + "-" + dayCode;
			
		return result;
	}
}