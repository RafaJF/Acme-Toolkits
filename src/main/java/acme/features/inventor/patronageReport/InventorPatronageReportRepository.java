package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport  findOnePatronageReportById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronageReportsByInventorId(int id);
	
	@Query("SELECT p FROM PatronageReport p WHERE p.patronage.id =:patronageId")
	Collection<PatronageReport> findPatronageReportsByPatronageId(Integer patronageId);
}
