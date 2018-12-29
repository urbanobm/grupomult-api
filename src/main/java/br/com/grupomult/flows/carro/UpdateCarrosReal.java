package br.com.grupomult.flows.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.entities.CarroPK;
import br.com.grupomult.entities.TipoCarro;
import br.com.grupomult.repositories.CarroRepository;
import br.com.grupomult.repositories.TipoCarroRepository;

public class UpdateCarrosReal {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private TipoCarroRepository tipoCarroRepository;

	@Autowired
	private InsertCarrosConverter insertCarrosConverter;
	
	@Autowired
	private InsertCarrosLoadValidate insertCarrosLoadValidate;
	

	public ResponseEntity<ResponseGetCarrosById> execute(br.com.grupomult.api.carro.models.Carro carro) {
		Carro carroEntity = insertCarrosConverter.executeConvertDomainToEntity(carro);
		TipoCarro tipoCarro = tipoCarroRepository.findByCode(carroEntity.getTipoCarro().getCode());
		carroEntity.setCarroPK(new CarroPK(tipoCarro)); 
		return insertCarrosLoadValidate.execute(salvarCarro(carroEntity));
	}

	private Carro salvarCarro(Carro carroEntity) {
		return carroRepository.save(carroEntity);
	}
	
}
