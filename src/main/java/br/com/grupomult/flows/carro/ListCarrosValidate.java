package br.com.grupomult.flows.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarros;

public class ListCarrosValidate {

	@Autowired
	private ListCarrosLoad load;

	public ResponseEntity<ResponseGetCarros> execute() {
		// Nothing to validate
		return load.execute();
	}

}
