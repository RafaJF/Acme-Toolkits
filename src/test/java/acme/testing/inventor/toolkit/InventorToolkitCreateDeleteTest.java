package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitCreateDeleteTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/createAndRemove-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String code, final String title, final String description, final String assamblyNotes, final String url) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.clickOnButton("Create toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assamblyNotes", assamblyNotes);
		super.fillInputBoxIn("url", url);
		
		super.clickOnSubmit("Create");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assamblyNotes", assamblyNotes);
		super.checkInputBoxHasValue("url", url);
		super.clickOnSubmit("Delete");
		
		super.signOut();
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/createAndRemove-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String code, final String title, final String description, final String assamblyNotes, final String url) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.clickOnButton("Create toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assamblyNotes", assamblyNotes);
		super.fillInputBoxIn("url", url);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
		
	}
}

