package br.com.grupomult.flows.carro;

import br.com.grupomult.converter.CarroConverter;
import br.com.grupomult.entities.Carro;

public class InsertCarrosConverter extends CarroConverter {

	public br.com.grupomult.api.carro.models.Carro executeConvertEntityToDomain(Carro carro) {
		return convertEntityToDomain(carro);
	}

	public Carro executeConvertDomainToEntity(br.com.grupomult.api.carro.models.Carro domain) {
		return convertDomainToEntity(domain);
	}

}
