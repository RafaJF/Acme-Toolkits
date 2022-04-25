package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.features.any.toolkit.AnyToolkitItemsService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item>{
	
	
	//Internal state --------------------------------------------------
	@Autowired	
	protected AnyToolItemsListService anyToolItemsListService;
	
	@Autowired
	protected AnyComponentItemListService anyComponentItemListService;
	
	@Autowired
	protected AnyItemsShowService itemsShowService;
	
	@Autowired
	protected AnyToolkitItemsService anyToolkitItemsService;

	
	//Constructors ----------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.itemsShowService);
		super.addCommand("list-published-tools","list", this.anyToolItemsListService);
		super.addCommand("list-published-components","list", this.anyComponentItemListService);
		super.addCommand("list-toolkit-items","list", this.anyToolkitItemsService);
	}
}

