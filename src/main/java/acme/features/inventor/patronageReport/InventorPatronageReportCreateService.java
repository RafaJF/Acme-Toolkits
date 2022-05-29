package acme.features.inventor.patronageReport;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import spamDetector.SpamDetector;

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
		
		patronageCode = request.getModel().getString("patronageCode");
		patronage=this.patronageRepository.findPatronagesByCode(patronageCode);
		entity.setPatronage(patronage);
		//
		final Integer numPatronageReports = this.reportRepository.numOfPatronagesReportByPatronage(patronageCode)+1;
		final String sequence = numPatronageReports.toString();
		String sequenceNumber;
		if(sequence.length() == 1) {
			sequenceNumber = patronageCode + ":000" + sequence;
		}else if(sequence.length() == 2){
			sequenceNumber = patronageCode + ":" + "00" + sequence ;
		}else if(sequence.length() ==3) {
			sequenceNumber = patronageCode + ":" + "0" + sequence;
		}else {
			sequenceNumber = patronageCode + ":" +sequence;
		}
		entity.setSequenceNumber(sequenceNumber);
		//
		
		request.bind(entity, errors,"creationMoment", "memorandum", "moreInfo");
		
		
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		
		model.setAttribute("confirmation", false);
		final Collection<Patronage> patronages = this.patronageRepository.findPatronagesByInventorId(request.getPrincipal().getActiveRoleId());
		final Collection<Patronage> publishedPatronages = new HashSet<>();
		
		for(final Patronage p:patronages) {
			if(p.isPublished()) {
				publishedPatronages.add(p);
			}
		}
		
		
		model.setAttribute("publishedPatronages", publishedPatronages);
		
		request.unbind(entity, model, "creationMoment", "memorandum", "moreInfo","patronage.code");
		
		
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
			
		if(!errors.hasErrors("memorandum")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.reportRepository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();

			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();

			res = SpamDetector.spamDetector(entity.getMemorandum(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);

			errors.state(request, res, "memorandum", "alert-message.form.spam");
		}
		
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.reportRepository.save(entity);
		
	}
	
	

}
