package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReport.PatronageReport;
import acme.entities.systemConfiguration.SystemConfiguration;
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
	
	@Query("select p from PatronageReport p where p.sequenceNumber = :sequenceNumber")
	PatronageReport  findOnePatronageReportByCode(String sequenceNumber);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor  findOneInventroById(String id);
	
	@Query("select count(p) from PatronageReport p where p.patronage.code = :code")
	Integer  numOfPatronagesReportByPatronage(String code);
  
    @Query("SELECT p FROM PatronageReport p WHERE p.patronage.id =:patronageId")
	Collection<PatronageReport> findPatronageReportsByPatronageId(Integer patronageId);
    
    @Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();

}
