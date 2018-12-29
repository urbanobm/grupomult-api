package br.com.grupomult.flows.carro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarros;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.repositories.CarroRepository;

public class ListCarrosLoad {

	@Autowired
	private CarroRepository repository;

	@Autowired
	private ListCarrosConverter converter;

	public ResponseEntity<ResponseGetCarros> execute() {
		return converter.execute((List<Carro>) repository.findAll());
	}

}
