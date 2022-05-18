package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorComponentListService	listComponentService;

	@Autowired
	protected InventorItemShowService	showService;
	
	@Autowired
	protected InventorToolListService	listToolService;
	
	@Autowired
	protected InventorToolkitItemListService inventorToolkitItemListService;
	

	@Autowired
	protected InventorItemCreateService createService;

	@Autowired
	protected InventorItemUpdateService updateService;
	
	@Autowired
	protected InventorItemDeleteService deleteService;
	
	@Autowired
	protected InventorItemPublishService publishService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-my-components", "list", this.listComponentService);
		super.addCommand("list-my-tools", "list", this.listToolService);
		super.addCommand("list-toolkit-items","list", this.inventorToolkitItemListService);
	
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
	}

}