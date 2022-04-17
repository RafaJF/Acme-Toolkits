package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Status;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, AdministratorDashboard> interface ----------------

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		AdministratorDashboard result;
		
		Integer totalNumberOfComponents;
		final Map<Pair<String, String>, Double> averageRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> deviationRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> minimumRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> maximumRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();

		Integer totalNumberOfTools;
		final Map<String, Double> averageRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> deviationRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> minimumRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> maximumRetailPriceOfToolsByCurrency =new HashMap<String, Double>();

		final Map<Status, Integer> totalNumberOfPatronagesByStatus = new HashMap<Status, Integer>();
		final Map<Status, Double> averageBudgetPatronagesByStatus = new HashMap<Status, Double>();
		final Map<Status, Double> deviationBudgetPatronagesByStatus = new HashMap<Status, Double>();
		final Map<Status, Double> minimumBudgetPatronagesByStatus = new HashMap<Status, Double>();
		final Map<Status, Double> maximumBudgetPatronagesByStatus = new HashMap<Status, Double>();

		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		totalNumberOfTools = this.repository.totalNumberOfTools();
		
		//averageRetailPriceOfComponentsByTechnologyAndCurrency
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x-> averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x-> averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x-> averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		//deviationRetailPriceOfComponentsByTechnologyAndCurrency
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x-> deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x-> deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x-> deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		//minimumRetailPriceOfComponentsByTechnologyAndCurrency
		this.repository.minimumRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x-> minimumRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.minimumRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x-> minimumRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.minimumRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x-> minimumRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		//maximumRetailPriceOfComponentsByTechnologyAndCurrency
		this.repository.maximumRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x->
		maximumRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maximumRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x->
		maximumRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of("EUR", x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maximumRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x->
		maximumRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of("GBP", x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
			);
	
		//averageRetailPriceOfToolsByCurrency
		final Double averageRetailPriceOfToolsByUSD =  this.repository.averageRetailPriceOfToolsByCurrency("USD");
		final Double averageRetailPriceOfToolsByEUR = this.repository.averageRetailPriceOfToolsByCurrency("EUR");
		final Double averagRetailPriceOfToolsByGBP = this.repository.averageRetailPriceOfToolsByCurrency("GBP");
		averageRetailPriceOfToolsByCurrency.put("USD", averageRetailPriceOfToolsByUSD);
		averageRetailPriceOfToolsByCurrency.put("EUR", averageRetailPriceOfToolsByEUR);
		averageRetailPriceOfToolsByCurrency.put("GBP", averagRetailPriceOfToolsByGBP);

		//deviationRetailPriceOfToolsByCurrency
		final Double deviationRetailPriceOfToolsByUSD = this.repository.deviationRetailPriceOfToolsByCurrency("USD");
		final Double deviationRetailPriceOfToolsByEUR = this.repository.deviationRetailPriceOfToolsByCurrency("EUR");
		final Double deviationRetailPriceOfToolsByGBP = this.repository.deviationRetailPriceOfToolsByCurrency("GBP");
		deviationRetailPriceOfToolsByCurrency.put("USD", deviationRetailPriceOfToolsByUSD);
		deviationRetailPriceOfToolsByCurrency.put("EUR", deviationRetailPriceOfToolsByEUR);
		deviationRetailPriceOfToolsByCurrency.put("GBP", deviationRetailPriceOfToolsByGBP);
		
		//minimumRetailPriceOfToolsByCurrency
		final Double minimumRetailPriceOfToolsByUSD = this.repository.minimumRetailPriceOfToolsByCurrency("USD");
		final Double minimunRetailPriceOfToolsByEUR = this.repository.minimumRetailPriceOfToolsByCurrency("EUR");
		final Double minimumRetailPriceOfToolsByGBP = this.repository.minimumRetailPriceOfToolsByCurrency("GBP");
		minimumRetailPriceOfToolsByCurrency.put("USD", minimumRetailPriceOfToolsByUSD);
		minimumRetailPriceOfToolsByCurrency.put("EUR", minimunRetailPriceOfToolsByEUR);
		minimumRetailPriceOfToolsByCurrency.put("GBP", minimumRetailPriceOfToolsByGBP);
		
		//maximumRetailPriceOfToolsByCurrency
		final Double maximumRetailPriceOfToolsByUSD = this.repository.maximumRetailPriceOfToolsByCurrency("USD");
		final Double maximumRetailPriceOfToolsByEUR = this.repository.maximumRetailPriceOfToolsByCurrency("EUR");
		final Double maximumRetailPriceOfToolsByGBP = this.repository.maximumRetailPriceOfToolsByCurrency("GBP");
		maximumRetailPriceOfToolsByCurrency.put("USD", maximumRetailPriceOfToolsByUSD);
		maximumRetailPriceOfToolsByCurrency.put("EUR", maximumRetailPriceOfToolsByEUR);
		maximumRetailPriceOfToolsByCurrency.put("GBP", maximumRetailPriceOfToolsByGBP);
		
		//totalNumberOfPatronagesByStatus
		final Integer numPatronagesProposed = this.repository.totalNumberOfPatronagesByStatus(Status.PROPOSED);
		final Integer numPatronagesAccepted = this.repository.totalNumberOfPatronagesByStatus(Status.ACCEPTED);
		final Integer numPatronagesDenied = this.repository.totalNumberOfPatronagesByStatus(Status.DENIED);
		totalNumberOfPatronagesByStatus.put(Status.PROPOSED, numPatronagesProposed);
		totalNumberOfPatronagesByStatus.put(Status.ACCEPTED, numPatronagesAccepted);
		totalNumberOfPatronagesByStatus.put(Status.DENIED, numPatronagesDenied);
		
		//averageBudgetPatronagesByStatus
		final Double averageBudgetPatronagesProposed = this.repository.averageBudgetPatronagesByStatus(Status.PROPOSED);
		final Double averageBudgetPatronagesAccepted = this.repository.averageBudgetPatronagesByStatus(Status.ACCEPTED);
		final Double averageBudgetPatronagesDenied = this.repository.averageBudgetPatronagesByStatus(Status.DENIED);
		averageBudgetPatronagesByStatus.put(Status.PROPOSED, averageBudgetPatronagesProposed);
		averageBudgetPatronagesByStatus.put(Status.ACCEPTED, averageBudgetPatronagesAccepted);
		averageBudgetPatronagesByStatus.put(Status.DENIED, averageBudgetPatronagesDenied);
		
		//deviationBudgetPatronagesByStatus
		final Double deviationBudgetPatronagesProposed = this.repository.deviationBudgetPatronagesByStatus(Status.PROPOSED);
		final Double deviationBudgetPatronagesAccepted = this.repository.deviationBudgetPatronagesByStatus(Status.ACCEPTED);
		final Double deviationBudgetPatronagesDenied = this.repository.deviationBudgetPatronagesByStatus(Status.DENIED);
		deviationBudgetPatronagesByStatus.put(Status.PROPOSED, deviationBudgetPatronagesProposed);
		deviationBudgetPatronagesByStatus.put(Status.ACCEPTED, deviationBudgetPatronagesAccepted);
		deviationBudgetPatronagesByStatus.put(Status.DENIED, deviationBudgetPatronagesDenied);
	
		//minimumBudgetPatronagesByStatus
		final Double minimumBudgetPatronagesProposed = this.repository.minimumBudgetPatronagesByStatus(Status.PROPOSED);
		final Double minimumBudgetPatronagesAccepted = this.repository.minimumBudgetPatronagesByStatus(Status.ACCEPTED);
		final Double minimumBudgetPatronagesDenied = this.repository.minimumBudgetPatronagesByStatus(Status.DENIED);
		minimumBudgetPatronagesByStatus.put(Status.PROPOSED, minimumBudgetPatronagesProposed);
		minimumBudgetPatronagesByStatus.put(Status.ACCEPTED, minimumBudgetPatronagesAccepted);
		minimumBudgetPatronagesByStatus.put(Status.DENIED, minimumBudgetPatronagesDenied);
		
		//maximumBudgetPatronagesByStatus
		final Double maximumBudgetPatronagesProposed = this.repository.maximumBudgetPatronagesByStatus(Status.PROPOSED);
		final Double maximumBudgetPatronagesAccepted = this.repository.maximumBudgetPatronagesByStatus(Status.ACCEPTED);
		final Double maximumBudgetPatronagesDenied = this.repository.maximumBudgetPatronagesByStatus(Status.DENIED);
		maximumBudgetPatronagesByStatus.put(Status.PROPOSED, maximumBudgetPatronagesProposed);
		maximumBudgetPatronagesByStatus.put(Status.ACCEPTED, maximumBudgetPatronagesAccepted);
		maximumBudgetPatronagesByStatus.put(Status.DENIED, maximumBudgetPatronagesDenied);
		
		result = new AdministratorDashboard();
		
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setTotalNumberOfPatronagesByStatus(totalNumberOfPatronagesByStatus);
		
		result.setAverageRetailPriceOfComponentsByTechnologyAndCurrency(averageRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfComponentsByTechnologyAndCurrency(deviationRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfComponentsByTechnologyAndCurrency(minimumRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfComponentsByTechnologyAndCurrency(maximumRetailPriceOfComponentsByTechnologyAndCurrency);

		result.setAverageRetailPriceOfToolsByCurrency(averageRetailPriceOfToolsByCurrency);
		result.setDeviationRetailPriceOfToolsByCurrency(deviationRetailPriceOfToolsByCurrency);
		result.setMinimumRetailPriceOfToolsByCurrency(minimumRetailPriceOfToolsByCurrency);
		result.setMaximumRetailPriceOfToolsByCurrency(maximumRetailPriceOfToolsByCurrency);
		
		result.setAverageBudgetPatronagesByStatus(averageBudgetPatronagesByStatus);
		result.setDeviationBudgetPatronagesByStatus(deviationBudgetPatronagesByStatus);
		result.setMinimumBudgetPatronagesByStatus(minimumBudgetPatronagesByStatus);
		result.setMaximumBudgetPatronagesByStatus(maximumBudgetPatronagesByStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfComponents", "totalNumberOfTools", "totalNumberOfPatronagesByStatus", 
			"averageRetailPriceOfComponentsByTechnologyAndCurrency", "deviationRetailPriceOfComponentsByTechnologyAndCurrency",
			"minimumRetailPriceOfComponentsByTechnologyAndCurrency", "maximumRetailPriceOfComponentsByTechnologyAndCurrency",
			"averageRetailPriceOfToolsByCurrency", "deviationRetailPriceOfToolsByCurrency", "minimumRetailPriceOfToolsByCurrency",
			"maximumRetailPriceOfToolsByCurrency", "averageBudgetPatronagesByStatus", "deviationBudgetPatronagesByStatus",
			"minimumBudgetPatronagesByStatus", "maximumBudgetPatronagesByStatus");
	}

}