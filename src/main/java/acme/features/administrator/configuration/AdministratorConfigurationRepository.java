package acme.features.administrator.configuration;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository{
	
	@Query("select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select c.code from Currencies c")
	List<String> findWorldCurrencies();

}
