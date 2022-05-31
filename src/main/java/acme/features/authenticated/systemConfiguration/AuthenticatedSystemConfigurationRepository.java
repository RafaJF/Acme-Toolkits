package acme.features.authenticated.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSystemConfigurationRepository extends AbstractRepository{
	
    @Query("SELECT s FROM SystemConfiguration s")
    SystemConfiguration findSystemConfiguration();

    @Query("SELECT s.acceptedCurrencies FROM SystemConfiguration s")
	String acceptedCurrencies();
}