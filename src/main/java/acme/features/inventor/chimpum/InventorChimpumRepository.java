package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {

	@Query("select i.chimpum from Item i where i.inventor.id =:id")
	Collection<Chimpum> findChimpumsByInventorId(int id);
	
	@Query("select c from Chimpum c where c.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.chimpum.id =:id")
	Item findOneItemByChimpumId(int id);
	
	@Query("SELECT c FROM Chimpum c WHERE c.code =:code")
	Chimpum findOneChimpumByCode(String code);

}