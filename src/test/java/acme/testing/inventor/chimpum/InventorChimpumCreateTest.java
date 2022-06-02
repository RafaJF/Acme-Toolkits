package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumCreateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title,final String budget, final String description, final String startDate, final String endDate, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my chimpums");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
	
		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Inventor","List my chimpums");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, budget);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
	
		super.signOut();
		
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final String title,final String budget, final String description, final String startDate, final String endDate, final String link) {
		//BUDGET NEGATIVE
		//Not valid start date
		//Not valid end date
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my chimpums");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
	
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		super.signOut();
		
	}
	@Test
	@Order(30)
	public void hackingCreateTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();
	}
	
	
}
