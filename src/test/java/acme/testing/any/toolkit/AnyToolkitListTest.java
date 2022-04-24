package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assamblyNotes, final String url, final String totalPrice) {
		
		super.clickOnMenu("Anonymous", "List of toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, totalPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assamblyNotes", assamblyNotes);
		super.checkInputBoxHasValue("url", url);
		super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.clickOnButton("Items");
		super.checkListingExists();
		
	}
}
