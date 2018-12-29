package br.com.grupomult.flows.carro;

import static br.com.grupomult.constants.MessageConstants.ERROR_INSERT_CARRO_OBJECT_NOT_FOUND;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.exceptions.HttpNotFoundException;

public class InsertCarrosLoadValidate {

	@Autowired
	private ListCarrosByIdConverter converter;
	
	public ResponseEntity<ResponseGetCarrosById> execute(Carro carroEntity) {
		if (Optional.ofNullable(carroEntity).isPresent()) {
			return converter.execute(carroEntity);
		} else {
			throw new HttpNotFoundException(ERROR_INSERT_CARRO_OBJECT_NOT_FOUND);
		}
	}
}
