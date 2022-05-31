package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Status;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
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
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	// AbstractShowService<Administrator, AdministratorDashboard> interface ----------------

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result;
		
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
		final Map<Pair<Status, String>, Double> averageBudgetPatronagesByStatus = new HashMap<Pair<Status, String>, Double>();
		final Map<Pair<Status, String>, Double> deviationBudgetPatronagesByStatus = new HashMap<Pair<Status, String>, Double>();
		final Map<Pair<Status, String>, Double> minimumBudgetPatronagesByStatus = new HashMap<Pair<Status, String>, Double>();
		final Map<Pair<Status, String>, Double> maximumBudgetPatronagesByStatus = new HashMap<Pair<Status, String>, Double>();

		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		totalNumberOfTools = this.repository.totalNumberOfTools();
		
		/*Chimpum*/
		final Double ratioOfItemsWithChimpum;
		final Map<String,Double> averageBudgetOfChimpumsByCurrency = new HashMap<String, Double>();;
		final Map<String,Double> deviationBudgetOfChimpumsByCurrency = new HashMap<String, Double>();;
		final Map<String,Double> minimumBudgetOfChimpumsByCurrency = new HashMap<String, Double>();;
		final Map<String,Double> maximumBudgetOfChimpumsByCurrency = new HashMap<String, Double>();;
		
		final List<String> currencies = new ArrayList<>();
		final String acceptedCurrencies = this.systemConfigRepository.findSystemConfiguration().getAcceptedCurrencies();
		final String[] field = acceptedCurrencies.split(",");
		currencies.add(field[0]);
		currencies.add(field[1]);
		currencies.add(field[2]);
		
		//averageRetailPriceOfComponentsByTechnologyAndCurrency
		for(final String currency: currencies) {
			this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency(currency).stream()
			.forEach(x-> averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currency, x.get(0).toString()),
					Double.parseDouble(x.get(1).toString())));
		}
		
		//deviationRetailPriceOfComponentsByTechnologyAndCurrency
		for(final String currency: currencies) {
			this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency(currency).stream()
			.forEach(x-> deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currency, x.get(0).toString()),
					Double.parseDouble(x.get(1).toString())));
		}
		
		//minimumRetailPriceOfComponentsByTechnologyAndCurrency
		for(final String currency: currencies) {
			this.repository.minimumRetailPriceOfComponentsByTechnologyAndCurrency(currency).stream()
			.forEach(x-> minimumRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currency, x.get(0).toString()),
					Double.parseDouble(x.get(1).toString())));
		}
		
		//maximumRetailPriceOfComponentsByTechnologyAndCurrency
		for(final String currency: currencies) {
			this.repository.maximumRetailPriceOfComponentsByTechnologyAndCurrency(currency).stream()
			.forEach(x->
			maximumRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currency, x.get(0).toString()),
					Double.parseDouble(x.get(1).toString())));
		}
	
		//averageRetailPriceOfToolsByCurrency
		for(final String currency: currencies) {
			final Double averageRetailPriceOfToolsByX =  this.repository.averageRetailPriceOfToolsByCurrency(currency);
			averageRetailPriceOfToolsByCurrency.put(currency, averageRetailPriceOfToolsByX);
		}
		
		//deviationRetailPriceOfToolsByCurrency
		for(final String currency: currencies) {
			final Double deviationRetailPriceOfToolsByX = this.repository.deviationRetailPriceOfToolsByCurrency(currency);
			deviationRetailPriceOfToolsByCurrency.put(currency, deviationRetailPriceOfToolsByX);
		}
		
		//minimumRetailPriceOfToolsByCurrency
		for(final String currency: currencies) {
			final Double minimumRetailPriceOfToolsByX = this.repository.minimumRetailPriceOfToolsByCurrency(currency);
			minimumRetailPriceOfToolsByCurrency.put(currency, minimumRetailPriceOfToolsByX);
		}
		
		//maximumRetailPriceOfToolsByCurrency
		for(final String currency: currencies) {
			final Double maximumRetailPriceOfToolsByX = this.repository.maximumRetailPriceOfToolsByCurrency(currency);
			maximumRetailPriceOfToolsByCurrency.put(currency, maximumRetailPriceOfToolsByX);
		}
		
		//totalNumberOfPatronagesByStatus
		final Integer numPatronagesProposed = this.repository.totalNumberOfPatronagesByStatus(Status.PROPOSED);
		final Integer numPatronagesAccepted = this.repository.totalNumberOfPatronagesByStatus(Status.ACCEPTED);
		final Integer numPatronagesDenied = this.repository.totalNumberOfPatronagesByStatus(Status.DENIED);
		totalNumberOfPatronagesByStatus.put(Status.PROPOSED, numPatronagesProposed);
		totalNumberOfPatronagesByStatus.put(Status.ACCEPTED, numPatronagesAccepted);
		totalNumberOfPatronagesByStatus.put(Status.DENIED, numPatronagesDenied);
		
		//averageBudgetPatronagesByStatus
		this.repository.averageBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> averageBudgetPatronagesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.averageBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> averageBudgetPatronagesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.averageBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> averageBudgetPatronagesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		//deviationBudgetPatronagesByStatus
		this.repository.deviationBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> deviationBudgetPatronagesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.deviationBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> deviationBudgetPatronagesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.deviationBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> deviationBudgetPatronagesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
	
		//minimumBudgetPatronagesByStatus
		this.repository.minimumBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> minimumBudgetPatronagesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.minimumBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> minimumBudgetPatronagesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.minimumBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> minimumBudgetPatronagesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		//maximumBudgetPatronagesByStatus
		this.repository.maximumBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> minimumBudgetPatronagesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.maximumBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> maximumBudgetPatronagesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.maximumBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> maximumBudgetPatronagesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		/*Chimpums*/
		
		//ratioOfItemsWithChimpum
		ratioOfItemsWithChimpum = this.repository.ratioOfItemsWithChimpum();
		
		//averageBudgetOfChimpumsByCurrency
		for(final String currency: currencies) {
			final Double averageBudgetOfChimpumsByX =  this.repository.averageBudgetOfChimpumByCurrency(currency);
			averageBudgetOfChimpumsByCurrency.put(currency, averageBudgetOfChimpumsByX);
		}
				
		//deviationBudgetOfChimpumsByCurrency
		for(final String currency: currencies) {
			final Double deviationBudgetOfChimpumsByX = this.repository.deviationBudgetOfChimpumByCurrency(currency);
			deviationBudgetOfChimpumsByCurrency.put(currency, deviationBudgetOfChimpumsByX);
		}
				
		//minimumBudgetOfChimpumsByCurrency
		for(final String currency: currencies) {
			final Double minimumBudgetOfChimpumsByX = this.repository.minimumBudgetOfChimpumByCurrency(currency);
			minimumBudgetOfChimpumsByCurrency.put(currency, minimumBudgetOfChimpumsByX);
		}
				
		//maximumBudgetOfChimpumsByCurrency
		for(final String currency: currencies) {
			final Double maximumBudgetOfChimpumsByX = this.repository.maximumBudgetOfChimpumByCurrency(currency);
			maximumBudgetOfChimpumsByCurrency.put(currency, maximumBudgetOfChimpumsByX);
		}
		
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
		
		/*Chimpums*/
		result.setRatioOfItemsWithChimpum(ratioOfItemsWithChimpum);
		result.setAverageBudgetOfChimpumsByCurrency(averageBudgetOfChimpumsByCurrency);
		result.setDeviationBudgetOfChimpumsByCurrency(deviationBudgetOfChimpumsByCurrency);
		result.setMinimumBudgetOfChimpumsByCurrency(minimumBudgetOfChimpumsByCurrency);
		result.setMaximumBudgetOfChimpumsByCurrency(maximumBudgetOfChimpumsByCurrency);
		
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
			"minimumBudgetPatronagesByStatus", "maximumBudgetPatronagesByStatus", 
			
			"ratioOfItemsWithChimpum",
			"averageBudgetOfChimpumsByCurrency", 
			"deviationBudgetOfChimpumsByCurrency", 
			"minimumBudgetOfChimpumsByCurrency",
			"maximumBudgetOfChimpumsByCurrency");
	}

}