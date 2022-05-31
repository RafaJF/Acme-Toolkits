package acme.testing.inventor.chimpum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpum-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description,final String budget,
		final String creationMoment,final String startDate,final String endDate, final String moreInfo) {
		
		super.signIn("inventor1", "inventor1");
	
		super.clickOnMenu("Inventor", "List my chimpums");
		
		super.sortListing(3, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationMoment",creationMoment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description",description);
		super.checkInputBoxHasValue("startDate",startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		
		super.signOut();
	}
}
