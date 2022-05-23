package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t from Toolkit t where t.id =:toolkitId")
	Toolkit findToolkitById(int toolkitId);

	@Query("SELECT t FROM Toolkit t where t.inventor.id =:id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("SELECT q FROM Quantity q")
	Collection<Quantity> findAllQuantity();
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit =:toolkit")
	Collection<Quantity> findAllQuantityOfToolkit(Toolkit toolkit);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findQuantitiesByToolkitId(int toolkitId);

	@Query("SELECT q.item FROM Quantity q WHERE q.id =:quantityId")
	Item findItemByQuantityId(int quantityId);
	
	@Query("SELECT i FROM Inventor i WHERE i.id =:inventorId")
	Inventor findInventorById(int inventorId);
	
	@Query("SELECT t FROM Toolkit t WHERE t.code =:code")
	Toolkit findToolkitByCode(String code);
	
	@Query("SELECT i FROM Item i")
	Collection<Item> findAllItem();
	
	@Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();
}