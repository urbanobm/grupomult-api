package br.com.grupomult.flows.carro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import br.com.grupomult.exceptions.HttpBadRequestException;
import br.com.grupomult.utils.CarroDomainEntityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class InserirCarrosValidateTest {

	@Autowired
	private InsertCarrosValidate insertCarrosValidate;

	@MockBean
	private InsertCarrosReal insertCarrosReal;
	
	@MockBean
	private Carro domain;

	@Before
	public void setUp() throws Exception {
		domain = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		when(insertCarrosReal.execute(domain)).thenReturn( CarroDomainEntityUtil.createResponseEntityResponseGetCarrosById(domain) );
	}
	
	@Test
	public void testValidateWithCarroFillSuccess() {
		Carro carroDadosValidos = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		ResponseEntity<ResponseGetCarrosById> response = insertCarrosValidate.execute(carroDadosValidos);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		Carro carro = body.getCarro();
		assertNotNull(carro);
	}

	@Test(expected = HttpBadRequestException.class)
	public void testValidateWithCarroNullHttpBadRequestException() {
		insertCarrosValidate.execute(null);
	}

	@Test(expected = HttpBadRequestException.class)
	public void testValidateWithCarroFaltandoDadosHttpBadRequestException() {
		insertCarrosValidate.execute(new Carro());
	}

}
