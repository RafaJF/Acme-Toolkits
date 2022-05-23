package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AnyChirpListTest extends TemporalAwareTestHarness{
	
	//Test cases ----------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/chirp/list-recent-chirps.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveToolTest(final int recordIndex, final int deltaDays, final String title, final String autor, final String body,
		final String email) {
	
		String moment;
		moment = super.computeDeltaMoment(deltaDays);
		
		super.clickOnMenu("Anonymous", "Chirp list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, autor);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		
	}
	

}
