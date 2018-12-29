package br.com.grupomult.flows.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.repositories.CarroRepository;

public class UpdateCarrosReal {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private InsertCarrosConverter insertCarrosConverter;
	
	@Autowired
	private InsertCarrosLoadValidate insertCarrosLoadValidate;
	

	public ResponseEntity<ResponseGetCarrosById> execute(br.com.grupomult.api.carro.models.Carro carro) {
		Carro executeEntity = insertCarrosConverter.executeConvertDomainToEntity(carro);
		return insertCarrosLoadValidate.execute(salvarCarro(executeEntity));
	}


	private Carro salvarCarro(Carro carroEntity) {
		return carroRepository.save(carroEntity);
	}
}
