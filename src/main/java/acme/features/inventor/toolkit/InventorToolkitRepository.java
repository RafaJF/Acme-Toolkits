package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);

	@Query("SELECT distinct(q.toolkit) from Quantity q where q.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("SELECT q FROM Quantity q")
	Collection<Quantity> findAllQuantity();
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit =:toolkit")
	Collection<Quantity> findAllQuantityOfToolkit(Toolkit toolkit);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findQuantitiesByToolkitId(int toolkitId);

	@Query("SELECT q.item FROM Quantity q WHERE q.id =:quantityId")
	Item findItemByQuantityId(int quantityId);
	
}