package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.announcement.Announcement;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AnnouncementController extends AbstractController<Authenticated, Announcement> {
	
	@Autowired
	protected AnnouncementListService announcementListService;

	@Autowired
	protected AnnouncementShowService announcementShowService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.announcementListService);
		super.addCommand("show", this.announcementShowService);
	}
}