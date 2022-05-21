//package acme.testing.inventor.quantity;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//
//import acme.testing.TestHarness;
//
//public class createQuantityAndRemove extends TestHarness{
//	@ParameterizedTest
//	@CsvFileSource(resources = "/inventor/quantity/createAndRemove-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
//	@Order(10)
//	public void positiveTest(final int recordIndex, final int itemRecordIndex,final String option,final String amount,final String name, final String type ) {
//		
//		super.signIn("inventor1", "inventor1");
//		super.clickOnMenu("Inventor", "List my toolkits");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.clickOnListingRecord(recordIndex);
//		super.checkFormExists();
//		super.clickOnButton("Add Item");
//		super.checkFormExists();
//		super.
//		super.fillInputBoxIn("amount", amount);
//		super.clickOnSubmit("Add");
//		super.clickOnMenu("Inventor", "List my toolkits");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.clickOnListingRecord(recordIndex);
//		super.checkFormExists();
//		super.clickOnButton("Items");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.checkColumnHasValue(itemRecordIndex, 1, name);
//		super.checkColumnHasValue(recordIndex, 2, type);
//		super.checkColumnHasValue(recordIndex, 4, amount);
//		
//		super.clickOnListingRecord(itemRecordIndex);
//		super.checkInputBoxHasValue("item.name", name);
//		super.checkInputBoxHasValue("item.itemType", type);
//		super.checkInputBoxHasValue("amount", amount);
//		
//		super.clickOnSubmit("Delete");
//		
//		
//		super.signOut();
//		
//	}
//
//}
