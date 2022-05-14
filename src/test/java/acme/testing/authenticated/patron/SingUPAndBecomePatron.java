package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class SingUPAndBecomePatron extends TestHarness {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/becomePatron.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivesSingUpAndBecomePatron(final String company, final String statement,final String info) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Account", "Become a patron");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Register");
		
		super.clickOnMenu("Account", "Patron data");
		super.checkSubmitExists("Update");
		
		
		super.signOut();
		
		
		
		
		
	}
	
	
}
