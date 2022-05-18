package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class createQuantityAndRemove extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/createAndRemove-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int itemRecordIndex,final String name,final String code, final String amount,final String technology) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Add Item");
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
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
		super.checkColumnHasValue(itemRecordIndex, 0, code);
		super.checkColumnHasValue(itemRecordIndex, 1, name);
		super.checkColumnHasValue(itemRecordIndex, 4, amount);
		super.clickOnListingRecord(itemRecordIndex);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		
		super.checkInputBoxHasValue("amount", amount);
		super.clickOnSubmit("Delete");
		super.signOut();
		
	}

}
