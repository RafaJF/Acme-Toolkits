package acme.testing.any.chirp;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chirp.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChirpRepository extends AbstractRepository{

	@Query("select chirp from Chirp chirp where chirp.creationMoment between '1900/01/01' and '1900/01/31'")
	Collection<Chirp> findChirps();

}
