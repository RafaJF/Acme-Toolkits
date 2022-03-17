package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double totalNumberOfComponents;
	
	
	Double averagePriceOfComponents;   //grouped by technology and currency
	Double deviationPriceOfComponents; //grouped by technology and currency
	Double minimumPriceOfComponents;   //grouped by technology and currency
	Double maximumPriceOfComponents;   //grouped by technology and currency
	
	Double totalNumberOfTools;

	Double averagePriceOfTools;   //grouped by currency
	Double deviationPriceOfTools; //grouped by currency
	Double minimumPriceOfTools;   //grouped by currency
	Double maximumPriceOfTools;   //grouped by currency
	
	Double totalNumberOfProposedPatronages;
	Double totalNumberOfAcceptedPatronages;
	Double totalNumberOfDeniedPatronages;
	
	
	Double averageBudgetOfProposedPatronages;
	Double deviationBudgetOfProposedPatronages;
	Double minimumBudgetOfProposedPatronages;
	Double maximumBudgetOfProposedPatronages;
	
	Double averageBudgetOfAcceptedPatronages;
	Double deviationBudgetOfAcceptedPatronages;
	Double minimumBudgetOfAcceptedPatronages;
	Double maximumBudgetOfAcceptedPatronages;
	
	Double averageBudgetOfDeniedPatronages;
	Double deviationBudgetOfDeniedPatronages;
	Double minimumBudgetOfDeniedPatronages;
	Double maximumBudgetOfDeniedPatronages;
	
	


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
