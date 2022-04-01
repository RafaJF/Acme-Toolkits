package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository {
	
	@Query("SELECT q from Quantity q where q.item.id = :componentId")
	Quantity findOneQuantityByComponentId(int componentId);
	
	@Query("SELECT i from Item i where i.id = :id")
	Item findOneComponentById(int id);
	
	@Query("SELECT q.item from Quantity q where q.item.itemType = acme.entities.item.ItemType.COMPONENT and q.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventor(int inventorId);

}