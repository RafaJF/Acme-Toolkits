package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {

	@Query("select c from Chimpum c where c.item.inventor.id =:id")
	Collection<Chimpum> findChimpumsByInventorId(int id);
	
	@Query("select c from Chimpum c where c.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("SELECT c FROM Chimpum c WHERE c.item.id =:id")
	Chimpum findOneChimpumByItemId(int id);

}