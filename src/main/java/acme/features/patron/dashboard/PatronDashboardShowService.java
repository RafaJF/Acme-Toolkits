
package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
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
		
		final Map<Status, Integer> numberOfPatronagesByStatus = new EnumMap<>(Status.class);
		
		final Integer numberOfProposedPatronages = this.patronDashboardRepository.numPatronageByStatus(Status.PROPOSED);
		final Integer numberOfAcceptedPatronages = this.patronDashboardRepository.numPatronageByStatus(Status.ACCEPTED);
		final Integer numberOfDeniedPatronages = this.patronDashboardRepository.numPatronageByStatus(Status.DENIED);
		
		numberOfPatronagesByStatus.put(Status.PROPOSED, numberOfProposedPatronages);
		numberOfPatronagesByStatus.put(Status.ACCEPTED, numberOfAcceptedPatronages);
		numberOfPatronagesByStatus.put(Status.DENIED, numberOfDeniedPatronages);
		
		final Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> avgBudgetByCurrencyAccep = this.patronDashboardRepository.averageNumberOfBudgetsByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> avgBudgetByCurrencyDenied = this.patronDashboardRepository.averageNumberOfBudgetsByCurrencyAndStatus(Status.DENIED);
		final List<String> avgBudgetByCurrencyProposed = this.patronDashboardRepository.averageNumberOfBudgetsByCurrencyAndStatus(Status.PROPOSED);

		final List<String> avgBudgetByCurrency = new ArrayList<String>();
		avgBudgetByCurrency.addAll(avgBudgetByCurrencyAccep);
		avgBudgetByCurrency.addAll(avgBudgetByCurrencyDenied);
		avgBudgetByCurrency.addAll(avgBudgetByCurrencyProposed);
		
		for(final String list: avgBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			averageNumberOfBudgetsByCurrencyAndStatus.put(key, money);
		}
		
		final Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> devBudgetByCurrencyAccep = this.patronDashboardRepository.deviationOfBudgetsByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> devBudgetByCurrencyDenied = this.patronDashboardRepository.deviationOfBudgetsByCurrencyAndStatus(Status.DENIED);
		final List<String> devBudgetByCurrencyProposed = this.patronDashboardRepository.deviationOfBudgetsByCurrencyAndStatus(Status.PROPOSED);

		final List<String> devBudgetByCurrency = new ArrayList<String>();
		devBudgetByCurrency.addAll(devBudgetByCurrencyAccep);
		devBudgetByCurrency.addAll(devBudgetByCurrencyDenied);
		devBudgetByCurrency.addAll(devBudgetByCurrencyProposed);
		
		for(final String list: devBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			deviationOfBudgetsByCurrencyAndStatus.put(key, money);
		}
		

		final Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> maxBudgetByCurrencyAccep = this.patronDashboardRepository.maxBudgetByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> maxBudgetByCurrencyDenied = this.patronDashboardRepository.maxBudgetByCurrencyAndStatus(Status.DENIED);
		final List<String> maxBudgetByCurrencyProposed = this.patronDashboardRepository.maxBudgetByCurrencyAndStatus(Status.PROPOSED);

		final List<String> maxBudgetByCurrency = new ArrayList<String>();
		maxBudgetByCurrency.addAll(maxBudgetByCurrencyAccep);
		maxBudgetByCurrency.addAll(maxBudgetByCurrencyDenied);
		maxBudgetByCurrency.addAll(maxBudgetByCurrencyProposed);
		
		for(final String list: maxBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			maxBudgetByCurrencyAndStatus.put(key, money);
		}
		
		final Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> minBudgetByCurrencyAccep = this.patronDashboardRepository.minBudgetByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> minBudgetByCurrencyDenied = this.patronDashboardRepository.minBudgetByCurrencyAndStatus(Status.DENIED);
		final List<String> minBudgetByCurrencyProposed = this.patronDashboardRepository.minBudgetByCurrencyAndStatus(Status.PROPOSED);

		final List<String> minBudgetByCurrency = new ArrayList<String>();
		minBudgetByCurrency.addAll(minBudgetByCurrencyAccep);
		minBudgetByCurrency.addAll(minBudgetByCurrencyDenied);
		minBudgetByCurrency.addAll(minBudgetByCurrencyProposed);
		
		for(final String list: minBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			minBudgetByCurrencyAndStatus.put(key, money);
		}
		
		
		
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

		request.unbind(entity, model,"numberOfPatronagesByStatus", "averageNumberOfBudgetsByCurrencyAndStatus",
			"deviationOfBudgetsByCurrencyAndStatus", "minBudgetByCurrencyAndStatus","maxBudgetByCurrencyAndStatus");
	
	}

}
