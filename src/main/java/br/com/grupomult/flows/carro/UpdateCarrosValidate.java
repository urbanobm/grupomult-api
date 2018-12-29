package br.com.grupomult.flows.carro;

import static br.com.grupomult.constants.MessageConstants.ERROR_INSERT_CARRO_INVALID_OBJECT;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.exceptions.HttpBadRequestException;

public class UpdateCarrosValidate {

	@Autowired
	private UpdateCarrosReal updateCarrosReal;

	public ResponseEntity<ResponseGetCarrosById> execute(Carro carro) {
		if (!Optional.ofNullable(carro).isPresent()
				|| carro.getId() == null
				|| carro.getCodigo() == null
				|| carro.getDescricao() == null
				|| carro.getTipoCarro() == null
				|| carro.getDataCriacao() == null
				|| carro.getDataAtualizacao() == null
				) {
			throw new HttpBadRequestException(ERROR_INSERT_CARRO_INVALID_OBJECT);
		}
		return updateCarrosReal.execute(carro);
	}
	
}
