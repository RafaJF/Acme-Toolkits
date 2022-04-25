
package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Toolkit t WHERE t.published = true")
	Collection<Toolkit> findAllToolkit();
	
	@Query("SELECT q FROM Quantity q")
	Collection<Quantity> findAllQuantity();
	
	@Query("SELECT t FROM Toolkit t WHERE t.id =:toolkitId")
	Toolkit findToolkitById(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit =:toolkit")
	Collection<Quantity> findAllQuantityOfToolkit(Toolkit toolkit);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findQuantitiesByToolkitId(int toolkitId);
	
	@Query("SELECT q.toolkit FROM Quantity q WHERE q.item.id =:itemId")
	Collection<Toolkit> findToolkitByItemId(int itemId);
	
	@Query("SELECT q.item FROM Quantity q WHERE q.id =:quantityId")
	Item findItemByQuantityId(int quantityId);
	

}
