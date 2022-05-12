package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.quantity.Quantity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository{
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id =:toolkitId ")
	Collection<Quantity> findQuantitiesByToolkitId(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.id =:quantityId")
	Quantity findQuantityById(int quantityId);
}
