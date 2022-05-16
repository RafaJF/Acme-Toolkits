package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationUpdateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrencies, 
		final String strongSpamTermsEn, final String strongSpamTermsEs, final String strongThreshold, final String weakSpamTermsEn, final String weakSpamTermsEs, final String weakThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTermsEn", strongSpamTermsEn);
		super.fillInputBoxIn("strongSpamTermsEs", strongSpamTermsEs);
		super.fillInputBoxIn("strongThreshold", strongThreshold);
		super.fillInputBoxIn("weakSpamTermsEn", weakSpamTermsEn);
		super.fillInputBoxIn("weakSpamTermsEs", weakSpamTermsEs);
		super.fillInputBoxIn("weakThreshold", weakThreshold);
		super.clickOnSubmit("Update Configuration");
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTermsEn", strongSpamTermsEn);
		super.checkInputBoxHasValue("strongSpamTermsEs", strongSpamTermsEs);
		super.checkInputBoxHasValue("strongThreshold", strongThreshold);
		super.checkInputBoxHasValue("weakSpamTermsEn", weakSpamTermsEn);
		super.checkInputBoxHasValue("weakSpamTermsEs", weakSpamTermsEs);
		super.checkInputBoxHasValue("weakThreshold", weakThreshold);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String acceptedCurrencies, final String strongSpamTerms,  
		final String strongThreshold, final String strongSpamTermsEn, final String strongSpamTermsEs, final String weakSpamTermsEn, final String weakSpamTermsEs, final String weakThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTermsEn", strongSpamTermsEn);
		super.fillInputBoxIn("strongSpamTermsEs", strongSpamTermsEs);
		super.fillInputBoxIn("strongThreshold", strongThreshold);
		super.fillInputBoxIn("weakSpamTermsEn", weakSpamTermsEn);
		super.fillInputBoxIn("weakSpamTermsEs", weakSpamTermsEs);
		super.fillInputBoxIn("weakThreshold", weakThreshold);
		super.clickOnSubmit("Update Configuration");
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/configuration/update");
		super.checkPanicExists();
		super.signOut();
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/configuration/update");
		super.checkPanicExists();
		super.signOut();
		super.navigate("/administrator/configuration/update");
		super.checkPanicExists();		
	}
}