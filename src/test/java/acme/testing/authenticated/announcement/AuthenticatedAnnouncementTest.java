package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedAnnouncementTest extends TemporalAwareTestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex, final int deltaDays, final String title, final String body, final String criticalFlag,
		final String info) {
		
		String moment;
		moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Announcement list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, title);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creationMoment", moment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("criticalFlag", criticalFlag);
		super.checkInputBoxHasValue("info", info);
		
		super.signOut();
	}
	
}
