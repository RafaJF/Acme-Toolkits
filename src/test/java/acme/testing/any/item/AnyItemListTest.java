package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-tools-published.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex, final String name, final String code, final String technology, final String description,
		final String retailPrice, final String info, final String itemType, final String published, final String inventor) {
		
		super.clickOnMenu("Anonymous", "Tools Published");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("published", published);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-components-published.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveComponentsTest(final int recordIndex, final String name, final String code, final String technology, final String description,
		final String retailPrice, final String info, final String itemType, final String published, final String inventor) {
		
		super.clickOnMenu("Anonymous", "Components Published");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("published", published);
	}
}
