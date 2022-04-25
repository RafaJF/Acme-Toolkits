package acme.features.authenticated.announcement;


import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnnouncementRespository extends AbstractRepository {
	
	@Query("SELECT a FROM Announcement a WHERE a.creationMoment > :deadline")
	Collection<Announcement> findAllRecentAnnouncements(Date deadline);
	
	@Query("SELECT a FROM Announcement a WHERE a.id = :id")
	Announcement findAnnouncementById(int id);
}