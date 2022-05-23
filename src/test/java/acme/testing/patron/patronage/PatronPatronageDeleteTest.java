package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		
		super.clickOnMenu("Patron", "Patronage list");
		super.checkNotErrorsExist();

		super.signOut();
	}
}
