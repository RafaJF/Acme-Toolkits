package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateStatusTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/updateStatus-positive.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateStatus(final int recordIndex, final String status ) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
	
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("status", status );
		super.clickOnSubmit("Update Status");
		super.clickOnMenu("Inventor", "List my patronages");
		super.sortListing(1, "asc");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkNotSubmitExists("Update Status");
		super.signOut();
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/updateStatus-negative.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdateStatus(final int recordIndex ) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
	
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkNotSubmitExists("Update Status");
		super.signOut();
		
	}


}
