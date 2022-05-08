package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport>{
	
	@Autowired
	protected InventorPatronageReportRepository inventorPatronageReportRepository;
	
	@Autowired
	protected InventorPatronageRepository inventorPatronageRepository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;

		result = !request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "sequenceNumber", "creationMoment","memorandum","moreInfo");
		
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment","memorandum","moreInfo");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("draftMode", entity.getPatronage());
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int masterId;
		Patronage patronage;

		masterId = request.getModel().getInteger("masterId");
		patronage = this.inventorPatronageRepository.findOnePatronageById(masterId);

		result = new PatronageReport();
		result.setSequenceNumber("");
		result.setCreationMoment(null);
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
		
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.inventorPatronageReportRepository.save(entity);
		
	}

}
