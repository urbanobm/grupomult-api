package br.com.grupomult.flows.carro;

import static br.com.grupomult.utils.DateUtils.ISO8601_COMPLETE_DATE_HOUR;
import static br.com.grupomult.utils.DateUtils.dateToString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.configuration.TestConfiguration;
import br.com.grupomult.repositories.TipoCarroRepository;
import br.com.grupomult.utils.CarroDomainEntityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class InserirCarrosConverterTest {

	@Autowired
	private InsertCarrosConverter insertCarrosConverter;

	@MockBean
	private TipoCarroRepository tipoCarroRepository;
	
	@Before
	public void setUp() throws Exception {
		when(tipoCarroRepository.findByCode(TipoCarroEnum.PASSEIO)).thenReturn(CarroDomainEntityUtil.criarTipoCarro(TipoCarroEnum.PASSEIO));
	}
	
	@Test
	public void testExecuteConvertDomainToEntity() {
		Date data = new Date();
		Carro carroDomain = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO, data, data);
		br.com.grupomult.entities.Carro carroDadosValidos = CarroDomainEntityUtil.criarEntityCarro(TipoCarroEnum.PASSEIO, data, data);
		br.com.grupomult.entities.Carro carroConverter = insertCarrosConverter.executeConvertDomainToEntity(carroDomain);
		assertNotNull(carroConverter);
		assertEquals(carroDadosValidos.getCodigo(), carroConverter.getCodigo());
		assertEquals(carroDadosValidos.getDescricao(), carroConverter.getDescricao());
		assertEquals(carroDadosValidos.getTipoCarro().getCode(), carroConverter.getTipoCarro().getCode());
		assertEquals(dateToString(carroDadosValidos.getDataAtualizacao(), ISO8601_COMPLETE_DATE_HOUR)
				, dateToString(carroConverter.getDataAtualizacao(), ISO8601_COMPLETE_DATE_HOUR));
		assertEquals(dateToString(carroDadosValidos.getDataAtualizacao(), ISO8601_COMPLETE_DATE_HOUR)
				, dateToString(carroConverter.getDataAtualizacao(), ISO8601_COMPLETE_DATE_HOUR));
	}

	
	@Test
	public void testExecuteConvertEntityToDomain() {
		Date data = new Date();
		br.com.grupomult.entities.Carro carroEntity = CarroDomainEntityUtil.criarEntityCarro(TipoCarroEnum.PASSEIO, data, data);
		Carro carroDadosValidos = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO, data, data);
		Carro carroConverter = insertCarrosConverter.executeConvertEntityToDomain(carroEntity);
		assertNotNull(carroConverter);
		assertEquals(carroDadosValidos.getCodigo(), carroConverter.getCodigo());
		assertEquals(carroDadosValidos.getDescricao(), carroConverter.getDescricao());
		assertEquals(carroDadosValidos.getTipoCarro(), carroConverter.getTipoCarro());
		assertEquals(carroDadosValidos.getDataAtualizacao(), carroConverter.getDataAtualizacao());
		assertEquals(carroDadosValidos.getDataAtualizacao(), carroConverter.getDataAtualizacao());
	}

	@Test()
	public void testConvertDomainToEntityNull() {
		br.com.grupomult.entities.Carro carroConverter = insertCarrosConverter.executeConvertDomainToEntity(null);
		assertNull(carroConverter);
	}

	@Test()
	public void testConvertEntityToDomainNull() {
		Carro carroConverter = insertCarrosConverter.executeConvertEntityToDomain(null);
		assertNull(carroConverter);
	}


}
