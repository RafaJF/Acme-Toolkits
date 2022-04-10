package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{

	@Query("SELECT i FROM Item i WHERE i.published = 1")
	Collection<Item> findAllItemPublished();
	
	@Query("select i from Item i where i.id = :id")
	Item findOneById(int id);
}
