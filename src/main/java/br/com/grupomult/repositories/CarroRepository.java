package br.com.grupomult.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.grupomult.entities.Carro;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {

	@Query("SELECT carro FROM Carro carro WHERE carro.carroPK.id = :id")
	Carro findById(@Param("id") Long id);
	
	
}
