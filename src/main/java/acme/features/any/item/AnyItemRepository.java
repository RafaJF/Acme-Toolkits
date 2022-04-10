package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{

	@Query("SELECT i FROM Item i WHERE i.itemType = acme.entities.item.ItemType.TOOL and i.published = 1")
	Collection<Item> findAllToolsPublished();
	
	@Query("SELECT i FROM Item i WHERE i.itemType = acme.entities.item.ItemType.COMPONENT and i.published = 1")
	Collection<Item> findAllComponentsPublished();
	
	@Query("SELECT i FROM Item i")
	Collection<Item> findAllItem();
	
	@Query("SELECT i FROM Item i WHERE i.published = 1")
	Collection<Item> findAllItemPublished();
	
	@Query("select i from Item i where i.id = :id")
	Item findOneById(int id);
}
