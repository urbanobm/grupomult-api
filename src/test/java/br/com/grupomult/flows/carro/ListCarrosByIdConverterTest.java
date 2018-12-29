package br.com.grupomult.flows.carro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.configuration.TestConfiguration;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.converter.CarroConverter;
import br.com.grupomult.utils.CarroDomainEntityUtil;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@PrepareForTest({ CarroConverter.class, LocalDate.class })
public class ListCarrosByIdConverterTest {

	private static final LocalDate MOCK_LOCAL_DATE_NOW = LocalDate.of(2016, Month.FEBRUARY, 9);

	private static final String CODIGO = "DRF1220";
	private static final String DATA_CRIACAO = "2015-02-09 12:00:00";
	private static final String DATA_ATUALIZACAO = "2018-02-09 13:00:00";
	private static final TipoCarroEnum TIPO_CARRO = TipoCarroEnum.PASSEIO;

	@Autowired
	private ListCarrosByIdConverter flow;

	@Before
	public void setUp() throws Exception {
		mockStatic(LocalDate.class);
		when(LocalDate.now()).thenReturn(MOCK_LOCAL_DATE_NOW);
	}

	@Test
	public void testConvertSuccess() {
		ResponseEntity<ResponseGetCarrosById> response = flow.execute(CarroDomainEntityUtil.createEntityCarro(CODIGO,DATA_CRIACAO, DATA_ATUALIZACAO, TIPO_CARRO));
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		br.com.grupomult.api.carro.models.Carro carro = body.getCarro();
		assertNotNull(carro);
		assertEquals(CODIGO, carro.getCodigo());
		assertEquals(DATA_CRIACAO, carro.getDataCriacao());
		assertEquals(TIPO_CARRO, carro.getTipoCarro());
	}

}
