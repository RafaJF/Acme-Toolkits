package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumUpdateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title ,final String budget, final String description, final String startDate, final String endDate, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my chimpums");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Inventor","List my chimpums");
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
	
		super.signOut();
		
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-negative-end-date.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void shouldNotUpdateBeacauseInvalidEndDate(final int recordIndex,final String endDate) {
		//Not valid endDate
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my chimpums");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("endDate", endDate);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
	
		super.signOut();
		
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-negative-code.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void shouldNotUpdateBecauseInvalidCode(final int recordIndex,final String code) {
		//Not valid code
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my chimpums");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
	
		super.signOut();
		
		
	}

}
