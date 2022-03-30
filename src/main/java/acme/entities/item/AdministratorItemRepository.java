package acme.entities.item;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorItemRepository extends AbstractRepository{

	@Query("SELECT i FROM Item i WHERE i.published = true")
	List<Item> findAllItemPublished();
	
}
