package acme.testing.authenticated.sysConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedConfigurationTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/system-configuration/system-configuration.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveSysConfigurationTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated","System configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		
		super.signOut();
		
		
	}

}
