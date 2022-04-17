package acme.entities.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);

	@Query("SELECT distinct(q.toolkit) from Quantity q where q.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
}