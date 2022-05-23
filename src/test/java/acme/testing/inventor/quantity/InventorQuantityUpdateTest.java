package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorQuantityUpdateTest extends TestHarness {
	
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/quantity/update-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final int itemRecordIndex,final String amount) {
			
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
			super.fillInputBoxIn("amount", amount);
			super.clickOnSubmit("Update");
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
			super.checkInputBoxHasValue("amount", amount);
			
			
			super.signOut();
			
		}
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/quantity/update-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeAmount(final int recordIndex, final int itemRecordIndex,final String amount) {
			
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
			super.fillInputBoxIn("amount", amount);
			super.clickOnSubmit("Update");
			super.checkErrorsExist();
			
			
			super.signOut();
			
		}
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/quantity/update-negative1.csv", encoding ="utf-8", numLinesToSkip = 1)
		@Order(10)
		public void shouldNotUpdate(final int recordIndex, final int itemRecordIndex) {
			
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
			
			super.checkNotSubmitExists("Update");
	
			
			
			super.signOut();
			
		}
}
