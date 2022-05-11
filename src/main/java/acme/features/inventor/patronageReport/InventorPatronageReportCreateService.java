package acme.features.inventor.patronageReport;

import java.util.Calendar;
import java.util.Date;

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
	protected InventorPatronageReportRepository inventorPatronageReportRepository;
	
	@Autowired
	protected InventorPatronageRepository inventorPatronageRepository;

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

		request.bind(entity, errors, "sequenceNumber", "creationMoment", "memorandum", "moreInfo");
		
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "moreInfo");
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Patronage patronage;
		Inventor inventor;

		inventor = this.inventorPatronageReportRepository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		
		patronage = new Patronage();
		patronage.setInventor(inventor);
		
		
		Date date;
		date = Calendar.getInstance().getTime();
		
		result = new PatronageReport();
		result.setSequenceNumber("");
		result.setCreationMoment(date);
		result.setMemorandum("");
		result.setMoreInfo("");
		result.setPatronage(patronage);
		

		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("sequenceNumber")) {
			PatronageReport duplicateCode;
			duplicateCode = this.inventorPatronageReportRepository.findOnePatronageReportByCode(entity.getSequenceNumber());
			errors.state(request, duplicateCode == null , "sequenceNumber", "inventor.patronageReport.form.error.duplicated");
		}
		if(!errors.hasErrors("moreInfo")) {
			boolean isUrl;
			isUrl = (entity.getMoreInfo().startsWith("http") || entity.getMoreInfo().startsWith("www")) && entity.getMoreInfo().contains(".");
			errors.state(request, isUrl == true, "moreInfo", "inventor.patronageReport.form.error.moreInfo");
		}
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.inventorPatronageReportRepository.save(entity);
		
	}

}
