package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;
@Service
public class InventorPatronageReportPatronageListService implements AbstractListService<Inventor,PatronageReport>{
	@Autowired
	InventorPatronageReportRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		
		assert request != null;
		
		Collection<PatronageReport> result;
		
		int patronageId;
		
		patronageId = request.getModel().getInteger("patronageId");
		
		result = this.repository.findPatronageReportsByPatronageId(patronageId);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum");
		
	}

}
