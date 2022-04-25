package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveAsAnonymous (final int recordIndex, final String name, final String surname,  final String email, final String username,  final String roles) {
//			super.signIn("anonymous", "anonymous");
			
			super.clickOnMenu("Anonymous", "User accounts");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, username);
			super.checkColumnHasValue(recordIndex, 1, name);
			super.checkColumnHasValue(recordIndex, 2, surname);
			super.checkColumnHasValue(recordIndex, 3, roles);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("username", username);
			super.checkInputBoxHasValue("identity.name", name);
			super.checkInputBoxHasValue("identity.surname", surname);
			super.checkInputBoxHasValue("identity.email", email);
			super.checkInputBoxHasValue("roleList", roles);
			
//			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void positiveAsAuthenticated (final int recordIndex, final String name, final String surname,  final String email, final String username,  final String roles) {
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Authenticated", "User accounts");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, username);
			super.checkColumnHasValue(recordIndex, 1, name);
			super.checkColumnHasValue(recordIndex, 2, surname);
			super.checkColumnHasValue(recordIndex, 3, roles);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("username", username);
			super.checkInputBoxHasValue("identity.name", name);
			super.checkInputBoxHasValue("identity.surname", surname);
			super.checkInputBoxHasValue("identity.email", email);
			super.checkInputBoxHasValue("roleList", roles);
			
			super.signOut();
		}
		
}
