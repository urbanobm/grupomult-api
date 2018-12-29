package br.com.grupomult.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.entities.TipoCarro;

@Repository
public interface TipoCarroRepository extends CrudRepository<TipoCarro, Integer> {

	@Query("SELECT tc FROM TipoCarro tc WHERE tc.code = :code")
	TipoCarro findByCode(@Param("code") TipoCarroEnum code);
	
}
