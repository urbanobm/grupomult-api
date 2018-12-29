package br.com.grupomult.flows.carro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.configuration.TestConfiguration;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.repositories.CarroRepository;
import br.com.grupomult.repositories.TipoCarroRepository;
import br.com.grupomult.utils.CarroDomainEntityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class InserirCarrosRealTest {

	@Autowired
	private InsertCarrosReal insertCarrosReal;
	
	@Autowired
	private CarroRepository carroRepository;
	
	@MockBean
	private TipoCarroRepository tipoCarroRepository;
	
	@MockBean
	private Carro domain;

	@Before
	public void setUp() throws Exception {
		domain = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		when(carroRepository.save(any(br.com.grupomult.entities.Carro.class))).thenReturn(CarroDomainEntityUtil.criarEntityCarro(TipoCarroEnum.PASSEIO));
		when(tipoCarroRepository.findByCode(TipoCarroEnum.PASSEIO)).thenReturn(CarroDomainEntityUtil.criarTipoCarro(TipoCarroEnum.PASSEIO));
		when(insertCarrosReal.execute(domain)).thenReturn( createResponseEntityResponseGetCarrosById() );
	}
	
	@Test
	public void testValidateWithCarroFillSuccess() {
		Carro carroDadosValidos = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		ResponseEntity<ResponseGetCarrosById> response = insertCarrosReal.execute(carroDadosValidos);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		Carro carro = body.getCarro();
		assertNotNull(carro);
	}

	@Test(expected = NullPointerException.class)
	public void testValidateWithCarroNullHttpBadRequestException() {
		insertCarrosReal.execute(null);
	}

	@Test(expected = NullPointerException.class)
	public void testValidateWithCarroFaltandoDadosHttpBadRequestException() {
		insertCarrosReal.execute(new Carro());
	}

	private ResponseEntity<ResponseGetCarrosById> createResponseEntityResponseGetCarrosById() {
		ResponseGetCarrosById response = new ResponseGetCarrosById();
		response.setCarro(domain);
		return ResponseEntity.ok(response);
	}

}
