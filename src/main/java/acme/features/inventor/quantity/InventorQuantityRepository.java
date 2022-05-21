package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository{
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id =:toolkitId ")
	Collection<Quantity> findQuantitiesByToolkitId(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.id =:quantityId")
	Quantity findQuantityById(int quantityId);
	
	@Query("SELECT i FROM Item i WHERE i.name =:itemName")
	Item findItemByName(String itemName);
	
	@Query("SELECT i FROM Item i")
	Collection<Item> findAllItems();
	
	@Query("SELECT q.toolkit FROM Quantity q WHERE q.id =:quantityId")
	Toolkit findToolkitByQuantityId(int quantityId);
	
	@Query("SELECT q FROM Quantity q WHERE q.item.id =:itemId AND q.toolkit.id=:toolkitId")
	Quantity findQuantityByItemIdAndToolkitId(int itemId, int toolkitId);
	
	@Query("SELECT i FROM Item i WHERE i.id =:itemId")
	Item findItemById(int itemId);
}
