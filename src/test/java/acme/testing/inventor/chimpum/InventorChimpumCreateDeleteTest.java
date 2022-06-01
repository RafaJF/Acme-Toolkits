package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumCreateDeleteTest extends TestHarness {
	
	@ParameterizedTest
	@Order(10)
	@CsvFileSource(resources = "/inventor/chimpum/chimpum-create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex, final String code,final String title, final String description,final String budget
		,final String startDate,final String endDate, final String moreInfo){
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		
		super.clickOnButton("Create Chimpum");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate",startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List my chimpums");

		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 3, startDate);
		super.checkColumnHasValue(recordIndex, 4, endDate);
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.clickOnMenu("Inventor", "List my components");
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		super.checkButtonExists("Create Chimpum");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpum-create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negativeTest(final int recordIndex, final String code, final String title, final String description,final String budget
		,final String startDate,final String endDate, final String moreInfo) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my components");
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		
		super.clickOnButton("Create Chimpum");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate",startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingCreateTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();
		
	}
	
	@Test
	@Order(30)
	public void hackingDeleteTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/chimpum/delete");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/chimpum/delete");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/chimpum/delete");
		super.checkPanicExists();
		super.signOut();

	}

}
