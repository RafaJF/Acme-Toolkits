package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationShowTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positive (final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String strongSpamTermsEn, final String strongSpamTermsEs, final String strongThreshold, final String weakSpamTermsEn, final String weakSpamTermsEs, final String weakThreshold) {
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "System configuration");
			super.checkFormExists();
			super.checkInputBoxHasValue("systemCurrency", systemCurrency);
			super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
			super.checkInputBoxHasValue("strongSpamTermsEn", strongSpamTermsEn);
			super.checkInputBoxHasValue("strongSpamTermsEs", strongSpamTermsEs);
			super.checkInputBoxHasValue("strongThreshold", strongThreshold);
			super.checkInputBoxHasValue("weakSpamTermsEn", weakSpamTermsEn);
			super.checkInputBoxHasValue("weakSpamTermsEs", weakSpamTermsEs);
			super.checkInputBoxHasValue("weakThreshold", weakThreshold);
			
			super.signOut();
		}
		
}
