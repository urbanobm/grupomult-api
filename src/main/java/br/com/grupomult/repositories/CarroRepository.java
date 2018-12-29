package br.com.grupomult.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.grupomult.entities.Carro;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {

}
