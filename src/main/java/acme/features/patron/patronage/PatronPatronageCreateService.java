package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron,Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractCreateService<Employer, Duty> interface -------------------------
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		
		assert request != null;
		 
		return true;
	}
	

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
	
		assert request != null;
		
		Patronage result;
		final Patron patron = this.repository.findPatronById(request.getPrincipal().getActiveRoleId());
		
		final Date creationMoment = new Date(System.currentTimeMillis()-1);
		
		result = new Patronage();
		result.setCreationMoment(creationMoment);
		result.setLegalStuff("");
		result.setMoreInfo("");
		result.setPatron(patron);
		result.setPublished(false);
		return result;
	}
	
	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Date moment = new Date(System.currentTimeMillis() -1);
		
		entity.setCreationMoment(moment);
		entity.setPublished(false);
		entity.setInventor(this.repository.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())));

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "moreInfo");
		
		//final String username =  request.getModel().getString("username");
		//final Inventor inventor =  this.repository.findInventorByUsername(username);
		//entity.setInventor(inventor);
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
        if(!errors.hasErrors("code")) {
        	
        	final Patronage patronageByCode =  this.repository.findPatronageByCode(entity.getCode());
        	errors.state(request, patronageByCode == null, "code", "patron.patronage.form.error.code");
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
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.budget-amount");
		}

           ////HAY QUE SEGUIR POR AQUIII
		
		if(!errors.hasErrors("startDate")) {
				
//			final Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.MONTH, 1);
//			calendar.add(Calendar.MILLISECOND, -1);

			final Date startDateMin = DateUtils.addMonths(entity.getCreationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(startDateMin), "startDate", "patron.patronage.form.error.start-date");			
		}
		
		if(!errors.hasErrors("endDate")) {
			
			final Date periodEndDate = DateUtils.addMonths(entity.getStartDate(), 1);
			final Date moment = entity.getEndDate();
			
			errors.state(request, moment.after(periodEndDate) , "endDate", "patron.patronage.form.error.end-date");
			
		}

	}	
	
	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "code", "legalStuff", "budget", "startDate", "endDate", "moreInfo", "inventorId","inventorFullname", "inventorEmail");	
		model.setAttribute("inventors", this.repository.findInventors());
		//model.setAttribute("inventorId", entity.getInventor().getId());
	}
	
	
	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	
	
	
	
}
