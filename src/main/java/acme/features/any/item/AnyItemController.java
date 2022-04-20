package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item>{
	
	
	//Internal state --------------------------------------------------
	@Autowired	
	protected AnyToolItemsListService anyToolItemsListService;
	
	@Autowired
	protected AnyItemsShowService itemsShowService;

	
	//Constructors ----------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.itemsShowService);
		super.addCommand("list-published-tools","list", this.anyToolItemsListService);
		
	}
}

