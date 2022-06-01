package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronage(final int recordIndex, final String code, final String legalStuff, final String budget, final String startDate, final String endDate, 
		final String moreInfo) {
		
		//añadir inventor al metodo
		super.signIn("patron1", "patron1");
	
		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate",startDate );
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
	
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
	
		super.signOut();
	}
	
	@ParameterizedTest	
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String legalStuff, final String budget, final String startDate, final String endDate, 
		final String moreInfo) {

		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate",startDate );
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/patron/patronage/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/patron/patronage/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage/create");
		super.checkPanicExists();
		super.signOut();
	}

}
