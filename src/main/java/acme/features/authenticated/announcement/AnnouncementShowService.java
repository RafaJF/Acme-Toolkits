package acme.features.authenticated.announcement;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AnnouncementShowService implements AbstractShowService<Authenticated, Announcement>  {
	
	@Autowired
	protected AnnouncementRespository announcementRespository;

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		final int announcementId = request.getModel().getInteger("id");
		final Announcement announcement = this.announcementRespository.findAnnouncementById(announcementId);
		
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		final Date deadline = calendar.getTime();
		
		if(announcement.getCreationMoment().after(deadline)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Announcement findOne(final Request<Announcement> request) {
		assert request != null;
		final int announcementId = request.getModel().getInteger("id");
		return this.announcementRespository.findAnnouncementById(announcementId);
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment","title","body","criticalFlag","info");
	}
}