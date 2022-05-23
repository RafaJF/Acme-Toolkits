package acme.testing.inventor.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorItemDeleteTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------
	@BeforeEach
	public void createItems() {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "aacomponent1");
		super.fillInputBoxIn("code", "RFV-801");
		super.fillInputBoxIn("technology", "technology");
		super.fillInputBoxIn("description", "description");
		super.fillInputBoxIn("retailPrice", "EUR 10.01");
		super.fillInputBoxIn("info", "");
		super.fillInputBoxIn("itemType", "COMPONENT");
		super.clickOnSubmit("Create");
		
		super.signOut();
	}
		
	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTest() {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);

		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
			
		// Comprobar que se ha borrado, no lo proporciona el framework 
				
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.clickOnSubmit("Publish");

		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkNotButtonExists("Delete");
		
		super.signOut();
	}
	
}
