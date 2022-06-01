/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FavouriteLinkTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void favouriteLink() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "54179154D: Ciézar Lanza, Eduardo");		
		super.checkCurrentUrl("https://www.youtube.com");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "26827296G: Jiménez Fernández, Rafael Ángel");		
		super.checkCurrentUrl("https://www.twitch.tv");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "53771290G: Rivero Gallardo, Jesús Antonio");		
		super.checkCurrentUrl("https://play.pokemonshowdown.com");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "76650547B: Salazar Caballero, Alberto");		
		super.checkCurrentUrl("https://play.hbomax.com");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "47394660D: Toro Valle, Daniel");		
		super.checkCurrentUrl("https://ev.us.es/webapps/blackboard/execute/modulepage/view?course_id=_52178_1&cmp_tab_id=_189480_1&mode=view");
		super.navigateHome();
		super.clickOnMenu("Anonymous", "53348944F: Villazán Torres, Francisco");		
		super.checkCurrentUrl("https://www.netflix.com/es");
	}

	
	// Ancillary methods ------------------------------------------------------ 
	
}