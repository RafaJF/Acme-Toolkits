package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("SELECT i from Item i where i.id = :id")
	Item findOneById(int id);
	
	@Query("SELECT i from Item i where i.itemType = acme.entities.item.ItemType.COMPONENT and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventor(int inventorId);

	@Query("SELECT i from Item i where i.itemType = acme.entities.item.ItemType.TOOL and i.inventor.id = :inventorId")
	Collection<Item> findToolsByInventor(int inventorId);
	
}