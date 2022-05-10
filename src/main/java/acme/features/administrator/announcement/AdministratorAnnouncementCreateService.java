package acme.features.administrator.announcement;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement>{

	@Autowired
	protected AdministratorAnnouncementRespository administratorAnnouncementRepository;


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	
		request.bind(entity, errors, "title", "body", "criticalFlag", "info");
	}
	
	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "title", "body", "criticalFlag", "info");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}
	
	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;
	
		final Announcement result;
		Date creationMoment;
	
		result = new Announcement();
		creationMoment =  Calendar.getInstance().getTime();
		result.setCreationMoment(creationMoment);
	
		return result;
	}
	
	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	
		boolean confirmation;
	
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "administrator.announcement.confirmation.error");
	}
	
	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;
	
		Date creationMoment;
	
		creationMoment = Calendar.getInstance().getTime();
		entity.setCreationMoment(creationMoment);
		this.administratorAnnouncementRepository.save(entity);
	}

}