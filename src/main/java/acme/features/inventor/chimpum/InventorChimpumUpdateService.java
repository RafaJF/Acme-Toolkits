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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor,Chimpum>{
	
	@Autowired
	protected InventorChimpumRepository repository;
	@Autowired
	protected InventorItemRepository itemRepository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		boolean result;
		
		int chimpumId;
		Item item;

		chimpumId = request.getModel().getInteger("id");
		item = this.repository.findOneItemByChimpumId(chimpumId);
		result = item.getInventor().getId() == request.getPrincipal().getActiveRoleId();
		
		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description","creationMoment", "budget", "startDate", "endDate", "moreInfo");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description","creationMoment", "budget", "startDate", "endDate", "moreInfo");	
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

        final SystemConfiguration systemConfig = this.systemConfigRepository.findSystemConfiguration();
        final String StrongEN = systemConfig.getStrongSpamTermsEn();
        final String StrongES = systemConfig.getStrongSpamTermsEs();
        final String WeakEN = systemConfig.getWeakSpamTermsEn();
        final String WeakES = systemConfig.getWeakSpamTermsEs();

        final double StrongThreshold = systemConfig.getStrongThreshold();
        final double WeakThreshold = systemConfig.getWeakThreshold();
		
        if(!errors.hasErrors("code")) {
        	final String inmutable = entity.getCode().substring(4,12);
        	final LocalDate cm =  entity.getCreationMoment().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	final String originalCode = this.codeGenerator(cm);
    		Chimpum existing;

    		existing = this.repository.findOneChimpumByCode(entity.getCode());
    		
        	errors.state(request, inmutable.equals(originalCode), "code", "inventor.chimpum.form.error.inmutable-date-code");
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
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
//	//Método auxiliar que genera automáticamente el código
//	
//	public String codeGenerator(final Date creationMoment) {
//		String result = "";
//		final Map<String,Integer> months = this.monthsMapGenerator();
//		
//		final String monthName = creationMoment.toString().substring(4, 7).toUpperCase();
//		final Integer month = months.get(monthName);
//		final String day = creationMoment.toString().substring(8, 10);
//		final String year = creationMoment.toString().substring(25, 29);
//
//		final String yearCode = year.substring(2, 4);
//		String monthCode= "";
//		String dayCode= "";
//			
//		if(month.toString().length()==1) {
//			monthCode = "0" + month.toString();
//		}else{
//			monthCode = month.toString();
//		}
//			
//		if(day.length()==1) {
//			dayCode = "0" + day;
//		}else {
//			dayCode = day;
//		}
//			
//		result = yearCode + "-" + monthCode + "-" + dayCode;
//			
//		return result;
//	}
//		
//	// Método para crear un diccionario con los meses
//	public Map<String,Integer> monthsMapGenerator(){
//		final Map<String,Integer> months = new HashMap<String,Integer>();
//
//		for (int i=1 ; i<13 ; i++) {
//			months.put(Month.of(i).toString(), i);
//		}
//			
//		return months;
//	}
	
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
