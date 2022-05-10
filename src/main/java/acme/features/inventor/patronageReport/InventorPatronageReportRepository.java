package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport  findOnePatronageReportById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronageReportsByInventorId(int id);
	
	@Query("select i from Inventor i where i.id =:id")
	Inventor findOneInventorById(int id);
	
	
}
