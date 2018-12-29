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

import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.configuration.TestConfiguration;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.exceptions.HttpNotFoundException;
import br.com.grupomult.repositories.TipoCarroRepository;
import br.com.grupomult.utils.CarroDomainEntityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ListCarrosByIdLoadValidateTest {

	@Autowired
	private ListCarrosByIdLoadValidate flow;

	@MockBean
	private ListCarrosByIdConverter converter;

	@MockBean
	private Carro entity;

	@MockBean
	private br.com.grupomult.api.carro.models.Carro domain;
	
	@MockBean
	private TipoCarroRepository tipoCarroRepository;

	@Before
	public void setUp() throws Exception {
		when(converter.execute(entity)).thenReturn(CarroDomainEntityUtil.createResponseEntityResponseGetCarrosById(domain));
	}

	@Test
	public void testValidateWithCarroFillSuccess() {
		ResponseEntity<ResponseGetCarrosById> response = flow.execute(entity);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		br.com.grupomult.api.carro.models.Carro carro = body.getCarro();
		assertNotNull(carro);
	}

	@Test(expected = HttpNotFoundException.class)
	public void testValidateWithCarroNullExpectedHttpNotFoundException() {
		flow.execute(null);
	}

}
