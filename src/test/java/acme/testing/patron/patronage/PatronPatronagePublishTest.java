package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronagePublishTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String legalStuff) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("legalStuff", legalStuff);
		
		super.clickOnSubmit("Publish");
		super.checkErrorsExist();

		super.signOut();
	}

}
