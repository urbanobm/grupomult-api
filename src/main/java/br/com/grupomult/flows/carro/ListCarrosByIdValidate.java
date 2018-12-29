package br.com.grupomult.flows.carro;

import static br.com.grupomult.constants.MessageConstants.ERROR_GET_CARROS_BY_ID_INVALID_ID;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.exceptions.HttpBadRequestException;

public class ListCarrosByIdValidate {

	@Autowired
	private ListCarrosByIdLoad load;

	public ResponseEntity<ResponseGetCarrosById> execute(Integer id) {
		if (!Optional.ofNullable(id).isPresent() || id < 0) {
			throw new HttpBadRequestException(ERROR_GET_CARROS_BY_ID_INVALID_ID);
		}
		return load.execute(id);
	}

}
