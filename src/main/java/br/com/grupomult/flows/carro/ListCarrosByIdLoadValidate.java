package br.com.grupomult.flows.carro;

import static br.com.grupomult.constants.MessageConstants.ERROR_GET_CARROS_BY_ID_NOT_FOUND;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.exceptions.HttpNotFoundException;

public class ListCarrosByIdLoadValidate {

	@Autowired
	private ListCarrosByIdConverter converter;

	public ResponseEntity<ResponseGetCarrosById> execute(Carro carro) {
		if (Optional.ofNullable(carro).isPresent()) {
			return converter.execute(carro);
		} else {
			throw new HttpNotFoundException(ERROR_GET_CARROS_BY_ID_NOT_FOUND);
		}
	}

}
