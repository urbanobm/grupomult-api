package br.com.grupomult.flows.carro;

import static br.com.grupomult.utils.OptionalUtils.ofNullableAndEmpty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.ResponseGetCarros;
import br.com.grupomult.converter.CarroConverter;

public class ListCarrosConverter extends CarroConverter {

	@Autowired
	private ListCarrosResponse response;

	public ResponseEntity<ResponseGetCarros> execute(List<br.com.grupomult.entities.Carro> carros) {

		/*
		 * Se a consulta do banco retornar uma lista nula ou vazia apenas é criada uma
		 * lista vazia do tipo de resposta da API, caso contrário é gerada uma lista com
		 * objetos de resposta da API baseados nos objetos do banco.
		 */
		List<Carro> carrosApi = ofNullableAndEmpty(carros).orElse(Collections.emptyList()).stream()
				.map(ListCarrosConverter::convertEntityToDomain).collect(Collectors.toList());

		return response.execute(carrosApi);

	}

}
