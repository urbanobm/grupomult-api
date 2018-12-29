package br.com.grupomult.flows.carro;

import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;

public class ListCarrosByIdResponse {

	public ResponseEntity<ResponseGetCarrosById> execute(Carro carro) {
		return ResponseEntity.ok(new ResponseGetCarrosById().carro(carro));
	}

}
