package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positive (final int recordIndex, final String sequenceNumber, final String creationMoment, final String memorandum, final String moreInfo, final String code) {
			super.signIn("patron1", "patron1");
			
			super.clickOnMenu("Patron", "Patronage report list");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, sequenceNumber);
			super.checkColumnHasValue(recordIndex, 1, creationMoment);
			super.checkColumnHasValue(recordIndex, 2, memorandum);
			super.checkColumnHasValue(recordIndex, 3, code);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
			super.checkInputBoxHasValue("creationMoment", creationMoment);
			super.checkInputBoxHasValue("memorandum", memorandum);
			super.checkInputBoxHasValue("moreInfo", moreInfo);
			super.checkInputBoxHasValue("patronage.code", code);
			
			super.signOut();
		}
		
}
