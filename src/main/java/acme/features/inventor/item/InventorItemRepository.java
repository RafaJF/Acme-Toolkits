package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository{

	@Query("select i from Item i where i.itemType ='TOOL' and i.inventor.id = :id")
	Collection<Item> findAllToolsByInventorId(int id);
	 
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);

	@Query("select i from Item i where i.id = :id")
	Item findItemlById(int id);
}
