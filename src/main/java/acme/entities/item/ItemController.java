package acme.entities.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class ItemController extends AbstractController <Any, Item>{

		@Autowired
		protected ItemComponentListService itemComponentListService; 

		@Autowired
		protected ItemComponentShowService itemComponentShowService;


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-my-components","list", this.itemComponentListService);
			super.addCommand("show",this.itemComponentShowService);
		}
}
