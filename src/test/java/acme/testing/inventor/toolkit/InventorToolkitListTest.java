package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int itemRecordIndex, final String code, final String title, final String description, final String assamblyNotes, final String url, final String totalPrice,
		final String itemName, final String itemCode, final String technology,final String itemDescription, final String retailPrice, final String itemType, final String amount) {
		
		super.signIn("inventor1", "inventor1");
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
		
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(itemRecordIndex, 0, itemCode);
		super.checkColumnHasValue(itemRecordIndex, 1, itemName);
		super.checkColumnHasValue(itemRecordIndex, 2, itemType);
		super.checkColumnHasValue(itemRecordIndex, 3, retailPrice);
		super.checkColumnHasValue(itemRecordIndex, 4, amount);
		super.clickOnListingRecord(itemRecordIndex);
		super.checkFormExists();
		

		super.checkInputBoxHasValue("item.name", itemName);
		super.checkInputBoxHasValue("item.code", itemCode);
		super.checkInputBoxHasValue("item.technology", technology);
		super.checkInputBoxHasValue("item.description", itemDescription);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("item.itemType", itemType);
		super.checkInputBoxHasValue("amount", amount);
		super.signOut();
		
		
	}
}
