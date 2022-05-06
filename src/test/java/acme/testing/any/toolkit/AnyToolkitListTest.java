package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexToolkit, final int recordIndexItem, final String code, final String title, final String description, final String assamblyNotes, final String url, final String totalPrice,
		final String name, final String retailPrice, final String technology, final String info, final String itemType) {
		
		super.clickOnMenu("Anonymous", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexToolkit, 0, title);
		super.checkColumnHasValue(recordIndexToolkit, 1, code);
		
		super.clickOnListingRecord(recordIndexToolkit);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assamblyNotes", assamblyNotes);
		super.checkInputBoxHasValue("url", url);
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexItem, 0, name);
		
		super.clickOnListingRecord(recordIndexItem);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("itemType", itemType);
		
		super.clickOnButton("Return");
		
		
		
		
	}
}
