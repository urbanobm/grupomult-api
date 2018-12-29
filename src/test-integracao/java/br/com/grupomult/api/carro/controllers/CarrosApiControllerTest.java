package br.com.grupomult.api.carro.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.grupomult.api.carro.controllers.CarrosApiController;
import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.api.carro.models.ResponseGetCarros;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.utils.CarroDomainEntityUtil;


@TestPropertySource(locations = "classpath:application.properties")
@Configuration
@EnableAutoConfiguration
@EntityScan("br.com.grupomult.entities")
@EnableJpaRepositories("br.com.grupomult.repositories")
@ComponentScan({"br.com.grupomult"})
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class CarrosApiControllerTest {

	@Autowired
	private CarrosApiController carrosApiController;
	
	private Carro carroDadosValidosInserido;

	@Before
	public void setUp() throws Exception {
		Carro carroDadosValidos = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		ResponseEntity<ResponseGetCarrosById> response = carrosApiController.adicionarCarro(carroDadosValidos);
		assertNotNull(response);
		carroDadosValidosInserido = response.getBody().getCarro();
		
		Carro carroDadosValido2 = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.UTILITARIO);
		response = carrosApiController.adicionarCarro(carroDadosValido2);
		assertNotNull(response);
	}

	@Test
	public void testInsertSuccess() {
		Carro carroDadosValidos = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		ResponseEntity<ResponseGetCarrosById> response = carrosApiController.adicionarCarro(carroDadosValidos);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		Carro carro = body.getCarro();
		assertNotNull(carro);
	}

	@Test
	public void testUpdateSuccess() {
		Carro carroDadosValidos = CarroDomainEntityUtil.criarDomainCarro(TipoCarroEnum.PASSEIO);
		ResponseEntity<ResponseGetCarrosById> response = carrosApiController.adicionarCarro(carroDadosValidos);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseGetCarrosById body = response.getBody();
		assertNotNull(body);

		Carro carroInserido = body.getCarro();
		assertNotNull(carroInserido);
		
		String descricaoAntes = carroInserido.getDescricao();
		carroInserido.setDescricao(descricaoAntes + "Teste");
		ResponseEntity<ResponseGetCarrosById> responseUpdate = carrosApiController.atualizarCarro(carroInserido);
		Carro carroAtualizado = responseUpdate.getBody().getCarro();
		
		assertNotEquals(descricaoAntes, carroAtualizado.getDescricao());
	}

	
	@Test
	public void testConsultarSuccess() {
		ResponseEntity<ResponseGetCarros> responseAntes = carrosApiController.list();
		int qtdCarrrosAntes = responseAntes.getBody().getCarros().size();
		assertTrue(qtdCarrrosAntes > 0);
	}

	@Test
	public void testDeletarSuccess() {
		ResponseEntity<ResponseGetCarros> responseAntes = carrosApiController.list();
		int qtdCarrrosAntes = responseAntes.getBody().getCarros().size();
		
		carrosApiController.deletarCarro(carroDadosValidosInserido.getId());
		
		ResponseEntity<ResponseGetCarros> responseDepois = carrosApiController.list();
		int qtdCarrrosDepois = responseDepois.getBody().getCarros().size();

		assertTrue(qtdCarrrosAntes > qtdCarrrosDepois);
	}

	@Test
	public void testDetailSuccess() {
		ResponseEntity<ResponseGetCarrosById> responseAntes = carrosApiController.detail(carroDadosValidosInserido.getId());
		Long idCarroConsultado = responseAntes.getBody().getCarro().getId();
		assertEquals(idCarroConsultado, carroDadosValidosInserido.getId());
	}
}
