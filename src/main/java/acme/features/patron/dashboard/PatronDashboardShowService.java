
package acme.features.patron.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	@Autowired
	protected PatronDashboardRepository patronDashboardRepository;


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		boolean res;
		res = request.getPrincipal().hasRole(Patron.class);
		return res;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {

		assert request != null;
		final PatronDashboard res;

		Map<Status, Integer> numberOfPatronagesByStatus;
		final Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus;

		numberOfPatronagesByStatus = new HashMap<Status,Integer>();
		final Integer numOfProposedPatronages = this.patronDashboardRepository.numPatronageByStatus(Status.PROPOSED);
		final Integer numOfAcceptedPatronages = this.patronDashboardRepository.numPatronageByStatus(Status.ACCEPTED);
		final Integer numOfDeniedPatronages = this.patronDashboardRepository.numPatronageByStatus(Status.DENIED);

		numberOfPatronagesByStatus.put(Status.ACCEPTED, numOfAcceptedPatronages);
		numberOfPatronagesByStatus.put(Status.DENIED, numOfDeniedPatronages);
		numberOfPatronagesByStatus.put(Status.PROPOSED, numOfProposedPatronages);

		averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();
		this.patronDashboardRepository.averageNumberOfBudgetsByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x -> averageNumberOfBudgetsByCurrencyAndStatus.put(Pair.of(Status.PROPOSED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.averageNumberOfBudgetsByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x -> averageNumberOfBudgetsByCurrencyAndStatus.put(Pair.of(Status.ACCEPTED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.averageNumberOfBudgetsByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x -> averageNumberOfBudgetsByCurrencyAndStatus.put(Pair.of(Status.DENIED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));

		deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();
		this.patronDashboardRepository.deviationOfBudgetsByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x -> deviationOfBudgetsByCurrencyAndStatus.put(Pair.of(Status.PROPOSED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.deviationOfBudgetsByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x -> deviationOfBudgetsByCurrencyAndStatus.put(Pair.of(Status.ACCEPTED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.deviationOfBudgetsByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x -> deviationOfBudgetsByCurrencyAndStatus.put(Pair.of(Status.DENIED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));

		minBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();
		this.patronDashboardRepository.minBudgetByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x -> minBudgetByCurrencyAndStatus.put(Pair.of(Status.PROPOSED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.minBudgetByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x -> minBudgetByCurrencyAndStatus.put(Pair.of(Status.ACCEPTED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.minBudgetByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x -> minBudgetByCurrencyAndStatus.put(Pair.of(Status.DENIED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));

		maxBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();
		this.patronDashboardRepository.maxBudgetByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x -> maxBudgetByCurrencyAndStatus.put(Pair.of(Status.PROPOSED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.maxBudgetByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x -> maxBudgetByCurrencyAndStatus.put(Pair.of(Status.ACCEPTED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.patronDashboardRepository.maxBudgetByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x -> maxBudgetByCurrencyAndStatus.put(Pair.of(Status.DENIED, x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));

		res = new PatronDashboard();
		res.setNumberOfPatronagesByStatus(numberOfPatronagesByStatus);
		res.setAverageNumberOfBudgetsByCurrencyAndStatus(averageNumberOfBudgetsByCurrencyAndStatus);
		res.setDeviationOfBudgetsByCurrencyAndStatus(deviationOfBudgetsByCurrencyAndStatus);
		res.setMinBudgetByCurrencyAndStatus(minBudgetByCurrencyAndStatus);
		res.setMaxBudgetByCurrencyAndStatus(maxBudgetByCurrencyAndStatus);

		return res;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfPatronagesByStatus", "averageNumberOfBudgetsByCurrencyAndStatus"
			,"deviationOfBudgetsByCurrencyAndStatus", "minBudgetByCurrencyAndStatus", //
			"maxBudgetByCurrencyAndStatus");

	}

}
