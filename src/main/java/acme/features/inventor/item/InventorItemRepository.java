package acme.features.inventor.item;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("SELECT i from Item i where i.id = :id")
	Item findItemById(int id);
}