package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractUpdateService<Patron, Patronage> -------------------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(patronageId);

		result = request.isPrincipal(patronage.getPatron()) && !patronage.isPublished();
		

		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int patronageId;
		patronageId = request.getModel().getInteger("id");

		result = this.repository.findPatronageById(patronageId);

		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code","status","creationMoment", "legalStuff", "budget", "startDate", "endDate", "moreInfo");
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		
		if(!errors.hasErrors("code")) {
			Patronage existingPatronage;
			existingPatronage = this.repository.findPatronageByCode(entity.getCode());
			
				errors.state(request, existingPatronage == null || existingPatronage.getId() == entity.getId(), "code", "patron.patronage.form.error.code-exists");
			
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


		if(!errors.hasErrors("startDate")) {
			final Date startDateMin = DateUtils.addMonths(entity.getCreationMoment(), 1);
	
			errors.state(request, entity.getStartDate().after(startDateMin), "startDate", "patron.patronage.form.error.start-date");			
		}
					
		
		
		if(!errors.hasErrors("endDate") && entity.getStartDate() !=null) {
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
		

		request.unbind(entity, model,"code","status","creationMoment", "legalStuff", "budget", "startDate", "endDate", "moreInfo","published");

		model.setAttribute("inventors", this.repository.findInventors());
		model.setAttribute("inventorId", entity.getInventor().getId());
		model.setAttribute("patronId", entity.getPatron().getId());
		model.setAttribute("inventorId", entity.getInventor().getId());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getIdentity().getEmail());
		model.setAttribute("inventorInfo", entity.getInventor().getInfo());
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);

	}

}
