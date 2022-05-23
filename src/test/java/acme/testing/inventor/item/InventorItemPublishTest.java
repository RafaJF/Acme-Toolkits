package acme.testing.inventor.item;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorItemPublishTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------
	@BeforeAll
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

		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0, 3, "\u2714");
		
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

		super.checkNotButtonExists("Publish");
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// El Framework actualmente no proporciona suficiente soporte para simular esta función, 
		// por lo que la prueba de piratería restante debe realizarse manualmente.
		// - Intentar publicar un item con otro rol que no sea el de inventor.
		// - Intentar publicar un item con otro usuario (inventor).
	}
}
