package acme.features.inventor.patronageReport;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport>{

	@Autowired
	protected InventorPatronageReportRepository reportRepository;
	
	@Autowired
	protected InventorPatronageRepository patronageRepository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
		
		String patronageCode;
		Patronage patronage;
		
		patronageCode = request.getModel().getString("patronage.code");
		patronage=this.patronageRepository.findPatronagesByCode(patronageCode);
		entity.setPatronage(patronage);
		
		request.bind(entity, errors, "sequenceNumber", "creationMoment", "memorandum", "moreInfo","patronage.code");
		
		
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "moreInfo","patronage.code");
		
		model.setAttribute("confirmation", false);
		final Collection<Patronage> patronages = this.patronageRepository.findPatronagesByInventorId(request.getPrincipal().getActiveRoleId());
		final Collection<Patronage> publishedPatronages = new HashSet<>();
		
		for(final Patronage p:patronages) {
			if(p.isPublished()) {
				publishedPatronages.add(p);
			}
		}
		
		model.setAttribute("publishedPatronages", publishedPatronages);
		
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		
		assert request != null;
		
		PatronageReport result;
		Patronage patronage;
		
		patronage = new Patronage();
		
		Date date;
		date = Calendar.getInstance().getTime();
		
		result = new PatronageReport();
		result.setCreationMoment(date);
		result.setPatronage(patronage);
		result.setPublished(false);

		

		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "inventor.patronageReport.error.confirmation");
		
		if(!errors.hasErrors("sequenceNumber")) {
			PatronageReport duplicateCode;
			duplicateCode = this.reportRepository.findOnePatronageReportByCode(entity.getSequenceNumber());
			errors.state(request, duplicateCode == null , "sequenceNumber", "inventor.patronageReport.form.error.duplicated");
		}
		if(!errors.hasErrors("moreInfo")) {
			boolean isUrl;
			isUrl = (entity.getMoreInfo().startsWith("http") || entity.getMoreInfo().startsWith("www")) && entity.getMoreInfo().contains(".");
			errors.state(request, isUrl, "moreInfo", "inventor.patronageReport.form.error.moreInfo");
		}
		
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.reportRepository.save(entity);
		
	}
	
	

}
