package acme.entities.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitController extends AbstractController<Inventor, Toolkit> {
	
	@Autowired
	protected InventorToolkitListService inventorToolkitListService;
	
	@Autowired
	protected InventorToolkitShowService inventorToolkitShowService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-mine", "list", this.inventorToolkitListService);
		super.addCommand("show", this.inventorToolkitShowService);
	}
}
