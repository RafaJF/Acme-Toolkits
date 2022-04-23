package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository {
	
	@Query("SELECT i from Item i where i.id = :id and i.itemType = acme.entities.item.ItemType.COMPONENT")
	Item findOneComponentById(int id);
	
	@Query("SELECT i from Item i where i.itemType = acme.entities.item.ItemType.COMPONENT and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventor(int inventorId);

}