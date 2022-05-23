package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
	
	@Query("select p from Patronage p where p.patron.id = :id")
	Collection<Patronage> findPatronages(Integer id);
	
	@Query("select patron from Patron patron WHERE patron.id=:id")
	Patron findPatronById(int id);
    
    @Query("select i from Inventor i WHERE i.id = :id")
    Inventor findInventorById(int id);
    
	@Query("select patronage from Patronage patronage where patronage.code = :code")
	Patronage findPatronageByCode(String code);
	
	@Query("select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
	
    @Query("select i from Inventor i")
    Collection<Inventor> findInventors();
	
	@Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.id =:id")
	Collection<PatronageReport> findAllPatronageReportsOfPatronages(Integer id);
	
	@Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();
}
