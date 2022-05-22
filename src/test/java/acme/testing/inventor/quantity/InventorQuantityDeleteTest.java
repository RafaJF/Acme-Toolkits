package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorQuantityDeleteTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/delete-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void shouldDeleteQuantity(final int recordIndex, final int itemRecordIndex) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(itemRecordIndex);
		super.checkFormExists();
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		
		super.signOut();
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/delete-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void shouldNotDeleteQuantity(final int recordIndex, final int itemRecordIndex) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(itemRecordIndex);
		super.checkFormExists();
		
		super.checkNotSubmitExists("Delete");
		
		
		
		super.signOut();
		
	}
}
