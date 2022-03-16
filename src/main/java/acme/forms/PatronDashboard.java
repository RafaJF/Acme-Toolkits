package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer totalNumberOfProposedPatronages;
	Integer totalNumberOfAcceptedPatronages;
	Integer totalNumberOfDeniedPatronages;
	
	/** Budget of Proposed Patronages grouped by currency **/
	Double averageBugetOfProposedPatronages;   
	Double deviationBugetOfProposedPatronages; 
	Double minimumBugetOfProposedPatronages;   
	Double maximumBugetOfProposedPatronages;   
	
	/** Budget of Accepted Patronages grouped by currency **/
	Double averageBugetOfAcceptedPatronages;   
	Double deviationBugetOfAcceptedPatronages; 
	Double minimumBugetOfAcceptedPatronages;   
	Double maximumBugetOfAcceptedPatronages;  
	
	/** Budget of Denied Patronages grouped by currency **/
	Double averageBugetOfDeniedPatronages;   
	Double deviationBugetOfDeniedPatronages; 
	Double minimumBugetOfDeniedPatronages;   
	Double maximumBugetOfDeniedPatronages;  
	
}