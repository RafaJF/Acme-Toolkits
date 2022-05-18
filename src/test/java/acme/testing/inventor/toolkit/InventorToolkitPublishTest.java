package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex) {
	super.signIn("inventor1", "inventor1");
	super.clickOnMenu("Inventor", "List my toolkits");
	super.checkListingExists();
	super.sortListing(0, "asc");
	super.clickOnListingRecord(recordIndex);
	super.checkFormExists();
	super.clickOnSubmit("Post");
	super.checkNotErrorsExist();
	super.signOut();
}
}