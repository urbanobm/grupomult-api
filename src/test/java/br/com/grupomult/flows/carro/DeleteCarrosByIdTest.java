package br.com.grupomult.flows.carro;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.grupomult.configuration.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class DeleteCarrosByIdTest {

	@Autowired
	private DeleteCarrosById deleteCarrosById;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDeleteSuccess() {
		deleteCarrosById.execute(new Random().nextInt());
	}
	
}