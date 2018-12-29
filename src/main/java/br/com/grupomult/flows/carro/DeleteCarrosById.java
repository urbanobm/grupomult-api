package br.com.grupomult.flows.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.repositories.CarroRepository;

public class DeleteCarrosById {

	@Autowired
	private CarroRepository repository;

	public ResponseEntity<Void> execute(Integer id) {
		try{
			repository.delete(id);
			return new ResponseEntity<Void>( HttpStatus.OK );
		}catch (Exception e) {
			 return new ResponseEntity<Void>( HttpStatus.NOT_FOUND );
		}
	}

}
