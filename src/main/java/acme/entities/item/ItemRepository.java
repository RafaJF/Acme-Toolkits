package acme.entities.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface ItemRepository extends AbstractRepository{

	@Query("SELECT i FROM Item i WHERE i.itemType = acme.entities.item.ItemType.COMPONENT")
	Collection<Item> findAllComponent();
	
	@Query("SELECT i FROM Item i WHERE i.itemType = acme.entities.item.ItemType.COMPONENT AND i.published = true")
	Collection<Item> findAllPublishedComponent();

	@Query("SELECT i FROM Item i WHERE i.id = :itemId")
	Item getItemById(int itemId);

}
