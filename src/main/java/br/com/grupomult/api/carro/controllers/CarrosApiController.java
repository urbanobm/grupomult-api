package br.com.grupomult.api.carro.controllers;

import static br.com.grupomult.constants.ApiConstants.GET_LIST_CARROS_BY_ID_PATH_ID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.ResponseGetCarros;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.flows.carro.DeleteCarrosById;
import br.com.grupomult.flows.carro.InsertCarrosValidate;
import br.com.grupomult.flows.carro.ListCarrosByIdValidate;
import br.com.grupomult.flows.carro.ListCarrosValidate;
import br.com.grupomult.flows.carro.UpdateCarrosValidate;
import io.swagger.annotations.ApiParam;

@RestController
public class CarrosApiController implements CarrosApi {
	
	@Autowired
	private InsertCarrosValidate insertCarrosValidate;
	
	@Autowired
	private UpdateCarrosValidate updateCarrosValidate;
	
	@Autowired
	private DeleteCarrosById deleteCarrosById;

	@Autowired
	private ListCarrosValidate listCarrosFlow;

	@Autowired
	private ListCarrosByIdValidate listCarrosByIdFlow;

	@Override
	public ResponseEntity<ResponseGetCarrosById> detail( @PathVariable(value = GET_LIST_CARROS_BY_ID_PATH_ID, required = true) Integer id) {
		return listCarrosByIdFlow.execute(id);
	}

	@Override
	public ResponseEntity<ResponseGetCarros> list() {
		return listCarrosFlow.execute();
	}
	
	@Override
    public ResponseEntity<ResponseGetCarrosById> adicionarCarro(@ApiParam(value = "Objeto carro a ser adicionado no estoque"  )  @Valid @RequestBody Carro body) {
		return insertCarrosValidate.execute(body);
	}
	
	@Override
    public ResponseEntity<ResponseGetCarrosById> atualizarCarro(@ApiParam(value = "Objeto carro a ser atualizado no estoque"  )  @Valid @RequestBody Carro body) {
		return updateCarrosValidate.execute(body);
	}

	@Override
	public ResponseEntity<Void> deletarCarro( @PathVariable(value = GET_LIST_CARROS_BY_ID_PATH_ID, required = true) Integer id) {
		return deleteCarrosById.execute(id);
	}

	

}
