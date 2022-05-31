//package acme.testing.inventor.chimpum;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//import acme.testing.TestHarness;
//
//public class InventorChimpumDeleteTest extends TestHarness {
//	
//	@Test
//	@Order(10)
//	public void positiveTest() {
//		
//		super.signIn("inventor1", "inventor1");
//		
//		super.clickOnMenu("Inventor", "List my chimpums");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.clickOnListingRecord(0);
//
//		super.clickOnSubmit("Delete");
//		super.checkNotErrorsExist();
//			
//		// Comprobar que se ha borrado, no lo proporciona el framework 
//				
//		super.signOut();
//	}
//}
