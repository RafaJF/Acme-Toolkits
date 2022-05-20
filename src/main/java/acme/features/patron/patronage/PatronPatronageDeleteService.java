package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.features.inventor.patronageReport.InventorPatronageReportRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository patronageRepository;
	
	@Autowired
	protected InventorPatronageReportRepository patronaReportRepository;

	// AbstractDeleteService<Patron, Patronage> -------------------------------------

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		final int patronageId = request.getModel().getInteger("id");
		final Patronage patronage = this.patronageRepository.findPatronageById(patronageId);

		result = request.isPrincipal(patronage.getPatron()) && !patronage.isPublished();

		return result;
	}


	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "moreInfo");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "startDate", "endDate", "moreInfo","published");
	}
	

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		final int patronageId = request.getModel().getInteger("id");

		result = this.patronageRepository.findPatronageById(patronageId);

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		Collection<PatronageReport> patronageReports;
		patronageReports = this.patronageRepository.findAllPatronageReportsOfPatronages(entity.getId());
		for (final PatronageReport pr : patronageReports) {
			this.patronaReportRepository.delete(pr);
		}

		this.patronageRepository.delete(entity);
	}

}
