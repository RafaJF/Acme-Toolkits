package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("SELECT i from Item i where i.id = :id")
	Item findOneById(int id);
	
	@Query("SELECT i from Item i where i.itemType = acme.entities.item.ItemType.COMPONENT and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventor(int inventorId);

	@Query("SELECT i from Item i where i.itemType = acme.entities.item.ItemType.TOOL and i.inventor.id = :inventorId")
	Collection<Item> findToolsByInventor(int inventorId);
	
	@Query("SELECT i from Inventor i WHERE i.id = :id")
	Inventor findInventorById(int id);
	
	@Query("SELECT i from Item i where i.code = :code")
	Item findItemByCode(String code);
	
	@Query("SELECT ac.acceptedCurrencies from SystemConfiguration ac")
	String findAcceptedCurrencies();
	
	@Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();
	
}