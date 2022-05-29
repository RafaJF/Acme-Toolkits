package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController  extends AbstractController<Inventor,Chimpum>{
	
	@Autowired
	protected InventorChimpumListService listService;
	
	@Autowired
	protected InventorChimpumShowService chimpumShowService;
	
	@Autowired
	protected InventorChimpumUpdateService chimpumUpdateService;
	
	@Autowired
	protected InventorChimpumDeleteService chimpumDeleteService;
	
	@Autowired
	protected InventorChimpumCreateService chimpumCreateService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.chimpumShowService);
		super.addCommand("update", this.chimpumUpdateService);
		super.addCommand("delete", this.chimpumDeleteService);
		super.addCommand("create", this.chimpumCreateService);
	}

}
