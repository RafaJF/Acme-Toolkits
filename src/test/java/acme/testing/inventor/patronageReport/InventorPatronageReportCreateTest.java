package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources="/inventor/patronageReport/create-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String memorandum, final String moreInfo,
		final String confirmation) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor","List my patronage reports");
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("confirmation", confirmation);
		
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 2, memorandum);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("memorandum",memorandum);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/inventor/patronageReport/create-negative.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(20) 
	public void negativeTest(final int recordIndex, final String memorandum, final String moreInfo,
		final String confirmation) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor","List my patronage reports");
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("confirmation", confirmation);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
	}

}
