//package acme.testing.inventor.patronage;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//
//import acme.testing.TestHarness;
////
////public class InventorPatronageUpdateStatusTest extends TestHarness {
////	@ParameterizedTest
////	@CsvFileSource(resources = "/inventor/patronage/updateStatus.csv", encoding = "utf-8",numLinesToSkip = 1)
////	@Order(10)
////	public void positiveToolTest(final int recordIndex, final String inventor, final String patron, final String status, final String code, final String legalStuff, final String budget,
////		final String creationMoment, final String startDate, final String endDate, final String moreInfo) {
////		
////		super.signIn("inventor1", "inventor1");
////		super.clickOnMenu("Inventor", "List my patronages");
////		super.checkListingExists();
////		super.sortListing(0, "asc");
////		
////		super.checkColumnHasValue(recordIndex, 0, status);
////		super.checkColumnHasValue(recordIndex, 1, code);
////		super.checkColumnHasValue(recordIndex, 2, legalStuff);
////		super.checkColumnHasValue(recordIndex, 3, budget);
////		super.checkColumnHasValue(recordIndex, 4, creationMoment);
////		
////		super.clickOnListingRecord(key);
////		super.checkFormExists();
////		super.checkInputBoxHasValue("status", status);
////		super.checkInputBoxHasValue("code", code);
////		super.checkInputBoxHasValue("legalStuff", legalStuff);
////		super.checkInputBoxHasValue("creationMoment", creationMoment);
////		super.checkInputBoxHasValue("startDate", startDate);
////		super.checkInputBoxHasValue("endDate", endDate);
////		super.checkInputBoxHasValue("moreInfo", moreInfo);
////		super.checkInputBoxHasValue("company", patron);
////		
////		super.signOut();
////		
////	}
////
////
////}
