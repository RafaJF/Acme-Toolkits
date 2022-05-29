package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{
	
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id")
	Collection<Item> findItemByInventorId(int id);
	
	@Query("SELECT c FROM Chimpum c")
	Collection<Chimpum> findAllChimpums();
	
	@Query("SELECT c FROM Chimpum c WHERE c.item.id = :id")
	Chimpum findChimpumByItemId(int id);
	
	@Query("SELECT c FROM Chimpum c WHERE c.id = :id")
	Chimpum findChimpumById(int id);
	
	@Query("SELECT ac.acceptedCurrencies from SystemConfiguration ac")
	String findAcceptedCurrencies();
	
	@Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();
	
	@Query("select i from Item i where i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
}
