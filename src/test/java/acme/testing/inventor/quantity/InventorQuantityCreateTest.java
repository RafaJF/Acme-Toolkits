package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorQuantityCreateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/create-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void shouldAddQuantity(final int recordIndex,final int itemRecordIndex, final String amount, final String itemName) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Add Item");
		super.checkFormExists();
		
		super.fillInputBoxIn("amount", amount);
		super.clickOnSubmit("Add");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(itemRecordIndex);
		
		super.checkInputBoxHasValue("item.name", itemName);
		
		super.checkInputBoxHasValue("amount", amount);
		
		
		
		super.signOut();
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/create-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void shouldNotAddQuantity(final int recordIndex,final int itemRecordIndex, final String amount) {
		//Add amount = 0 
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Add Item");
		super.checkFormExists();
		
		super.fillInputBoxIn("amount", amount);
		super.clickOnSubmit("Add");
		super.checkErrorsExist();
		
	}

}
