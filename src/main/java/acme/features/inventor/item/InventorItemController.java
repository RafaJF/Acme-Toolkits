package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {
	
	@Autowired
	protected InventorItemShowService showService;
	
	@Autowired
	protected InventorToolkitItemListService toolkitItemsListService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-toolkit-items","list", this.toolkitItemsListService);
		super.addCommand("show", this.showService);
	}
	
}
