package acme.testing.authenticated.inventor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedInventorCreateTest extends TestHarness {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/inventor/becomeInventor.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAuthenticatedInventor (final String company, final String statement,final String info) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Account", "Become a inventor");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Register");
		
		super.clickOnMenu("Account", "Inventor data");
		super.checkSubmitExists("Update");
		
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/inventor/negativeBecomeInventor.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeAuthenticatedInventor(final String company, final String statement,final String info) {
		
		super.signIn("patron2", "patron2");
		
		super.clickOnMenu("Account", "Become a inventor");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Register");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/authenticated/inventor/create");
		super.checkPanicExists();

		super.signIn("inventor1", "inventor1");
		super.navigate("/authenticated/inventor/create");
		super.checkPanicExists();
		super.signOut();
	}

	
	
}
