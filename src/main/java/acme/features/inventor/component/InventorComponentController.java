//package acme.features.inventor.component;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import acme.entities.item.Item;
//import acme.framework.controllers.AbstractController;
//import acme.roles.Inventor;
//
//@Controller
//public class InventorComponentController extends AbstractController<Inventor, Item> {
//
//	// Internal state ---------------------------------------------------------
//
//	@Autowired
//	protected InventorComponentListService	listService;
//
//	@Autowired
//	protected InventorComponentShowService	showService;
//
//	// Constructors -----------------------------------------------------------
//
//	@PostConstruct
//	protected void initialise() {
//		super.addCommand("show", this.showService);
//		super.addCommand("list-my-components", "list", this.listService);
//	}
//
//}
