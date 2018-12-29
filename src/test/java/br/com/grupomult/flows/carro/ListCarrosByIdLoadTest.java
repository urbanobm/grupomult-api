package br.com.grupomult.flows.carro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Random;

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
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.configuration.TestConfiguration;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.repositories.CarroRepository;
import br.com.grupomult.repositories.TipoCarroRepository;
import br.com.grupomult.utils.CarroDomainEntityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ListCarrosByIdLoadTest {

	@Autowired
	private ListCarrosByIdLoad flow;

	@Autowired
	private CarroRepository repository;
	
	@MockBean
	private TipoCarroRepository tipoCarroRepository;

	@MockBean
	private ListCarrosByIdLoadValidate validate;

	@MockBean
	private Carro entity;

	@MockBean
	private br.com.grupomult.api.carro.models.Carro domain;

	@Before
	public void setUp() throws Exception {
		when(repository.findById(anyLong())).thenReturn(entity);
		when(validate.execute(entity)).thenReturn(CarroDomainEntityUtil.createResponseEntityResponseGetCarrosById(domain));
	}

	@Test
	public void testLoadSuccess() {
		ResponseEntity<ResponseGetCarrosById> response = flow.execute(new Random().nextLong());
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		br.com.grupomult.api.carro.models.Carro carro = body.getCarro();
		assertNotNull(carro);
	}

}
