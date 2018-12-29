package br.com.grupomult.flowdefinitions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.grupomult.configuration.TestConfiguration;
import br.com.grupomult.flows.carro.DeleteCarrosById;
import br.com.grupomult.flows.carro.InsertCarrosConverter;
import br.com.grupomult.flows.carro.InsertCarrosLoadValidate;
import br.com.grupomult.flows.carro.InsertCarrosReal;
import br.com.grupomult.flows.carro.InsertCarrosValidate;
import br.com.grupomult.flows.carro.ListCarrosByIdConverter;
import br.com.grupomult.flows.carro.ListCarrosByIdLoad;
import br.com.grupomult.flows.carro.ListCarrosByIdLoadValidate;
import br.com.grupomult.flows.carro.ListCarrosByIdResponse;
import br.com.grupomult.flows.carro.ListCarrosByIdValidate;
import br.com.grupomult.flows.carro.ListCarrosConverter;
import br.com.grupomult.flows.carro.ListCarrosLoad;
import br.com.grupomult.flows.carro.ListCarrosResponse;
import br.com.grupomult.flows.carro.ListCarrosValidate;
import br.com.grupomult.flows.carro.UpdateCarrosReal;
import br.com.grupomult.flows.carro.UpdateCarrosValidate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class FlowDefinitionCarroTest {

	@Autowired
	private ApplicationContext applicationContest;

	@Test
	public void testFlowListCarrosExistsSuccess() {
		assertNotNull(applicationContest.getBean("listCarrosValidate"));
		assertNotNull(applicationContest.getBean("listCarrosLoad"));
		assertNotNull(applicationContest.getBean("listCarrosConverter"));
		assertNotNull(applicationContest.getBean("listCarrosResponse"));
	}

	@Test
	public void testFlowListCarrosInstanceofSuccess() {
		assertTrue(applicationContest.getBean("listCarrosValidate") instanceof ListCarrosValidate);
		assertTrue(applicationContest.getBean("listCarrosLoad") instanceof ListCarrosLoad);
		assertTrue(applicationContest.getBean("listCarrosConverter") instanceof ListCarrosConverter);
		assertTrue(applicationContest.getBean("listCarrosResponse") instanceof ListCarrosResponse);
	}

	@Test
	public void testFlowListCarrosByIdExistsSuccess() {
		assertNotNull(applicationContest.getBean("listCarrosByIdValidate"));
		assertNotNull(applicationContest.getBean("listCarrosByIdLoad"));
		assertNotNull(applicationContest.getBean("listCarrosByIdLoadValidate"));
		assertNotNull(applicationContest.getBean("listCarrosByIdConverter"));
		assertNotNull(applicationContest.getBean("listCarrosByIdResponse"));
	}

	@Test
	public void testFlowListCarrosByIdInstanceofSuccess() {
		assertTrue(applicationContest.getBean("listCarrosByIdValidate") instanceof ListCarrosByIdValidate);
		assertTrue(applicationContest.getBean("listCarrosByIdLoad") instanceof ListCarrosByIdLoad);
		assertTrue(applicationContest.getBean("listCarrosByIdLoadValidate") instanceof ListCarrosByIdLoadValidate);
		assertTrue(applicationContest.getBean("listCarrosByIdConverter") instanceof ListCarrosByIdConverter);
		assertTrue(applicationContest.getBean("listCarrosByIdResponse") instanceof ListCarrosByIdResponse);
	}

	@Test
	public void testFlowInsertCarrosSuccess() {
		assertNotNull(applicationContest.getBean("insertCarrosValidate"));
		assertNotNull(applicationContest.getBean("insertCarrosConverter"));
		assertNotNull(applicationContest.getBean("insertCarrosReal"));
		assertNotNull(applicationContest.getBean("insertCarrosLoadValidate"));
	}

	@Test
	public void testFlowInsertCarrosInstanceofSuccess() {
		assertTrue(applicationContest.getBean("insertCarrosValidate") instanceof InsertCarrosValidate);
		assertTrue(applicationContest.getBean("insertCarrosConverter") instanceof InsertCarrosConverter);
		assertTrue(applicationContest.getBean("insertCarrosReal") instanceof InsertCarrosReal);
		assertTrue(applicationContest.getBean("insertCarrosLoadValidate") instanceof InsertCarrosLoadValidate);
	}

	@Test
	public void testFlowUpdateCarrosSuccess() {
		assertNotNull(applicationContest.getBean("updateCarrosValidate"));
		assertNotNull(applicationContest.getBean("updateCarrosReal"));
	}

	@Test
	public void testFlowUpdateCarrosInstanceofSuccess() {
		assertTrue(applicationContest.getBean("updateCarrosValidate") instanceof UpdateCarrosValidate);
		assertTrue(applicationContest.getBean("updateCarrosReal") instanceof UpdateCarrosReal);
	}


	@Test
	public void testFlowDeleteCarrosSuccess() {
		assertNotNull(applicationContest.getBean("deleteCarrosById"));
	}

	@Test
	public void testFlowDeleteCarrosInstanceofSuccess() {
		assertTrue(applicationContest.getBean("deleteCarrosById") instanceof DeleteCarrosById);
	}

	
}
