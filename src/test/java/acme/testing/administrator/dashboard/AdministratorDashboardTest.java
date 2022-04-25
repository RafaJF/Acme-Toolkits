package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorDashboardTest extends TestHarness{
	

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/administrator-dashboard/administrator-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positivePatronDashboardTest(final int recordIndex) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator","Dashboard");
		super.checkFormExists();
		super.signOut();
		
		
	}

}
