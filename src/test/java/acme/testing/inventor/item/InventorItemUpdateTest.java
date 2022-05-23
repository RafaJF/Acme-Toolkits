package acme.testing.inventor.item;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemUpdateTest extends TestHarness{
	
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

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String info, final String itemType) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("itemType", itemType);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0, 0, name);
		super.checkColumnHasValue(0, 1, code);
		super.checkColumnHasValue(0, 2, retailPrice);
		super.checkColumnHasValue(0, 3, "\u274C");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("itemType", itemType);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String info, final String itemType) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("itemType", itemType);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// El Framework actualmente no proporciona suficiente soporte para simular esta función, 
		// por lo que la prueba de piratería restante debe realizarse manualmente.
		// - Intentar editar un item con otro rol que no sea el de inventor.
		// - Intentar editar un item con otro usuario (inventor).
		// - Intentar editar un item publicado.
	}
}
