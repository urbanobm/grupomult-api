package br.com.grupomult.configurations;

import static br.com.grupomult.api.animal.models.Animal.SpeciesEnum.CANINE;
import static br.com.grupomult.api.animal.models.Animal.SpeciesEnum.FELINE;
import static br.com.grupomult.api.carro.models.Carro.TipoCarroEnum.PASSEIO;
import static br.com.grupomult.api.carro.models.Carro.TipoCarroEnum.UTILITARIO;
import static br.com.grupomult.utils.DateUtils.ISO8601_COMPLETE_DATE_HOUR;
import static br.com.grupomult.utils.DateUtils.stringToDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.grupomult.api.animal.models.Animal.SpeciesEnum;
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.entities.Animal;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.entities.Species;
import br.com.grupomult.entities.TipoCarro;
import br.com.grupomult.repositories.AnimalRepository;
import br.com.grupomult.repositories.CarroRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase implements CommandLineRunner {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private CarroRepository carroRepository;

	@Override
	public void run(String... args) throws Exception {
		initDatabaseAnimal();
		initDatabaseCarro();
	}

	public void initDatabaseAnimal() {
		log.info(
				"Preloading " + animalRepository.save(createAnimal("Janis", "2015-02-09 11:00:00", CANINE)).toString());
		log.info(
				"Preloading " + animalRepository.save(createAnimal("Rocky", "2014-10-15 11:00:00", FELINE)).toString());
	}

	public void initDatabaseCarro() {
		log.info("Preloading " + carroRepository
				.save(createCarro("PLR1235", "Hiunday i30", "2017-01-01 11:00:00", "2017-02-01 11:00:00", PASSEIO))
				.toString());
		log.info("Preloading " + carroRepository
				.save(createCarro("PLR1235", "Hiunday ix35", "2018-01-01 11:00:00", "2018-02-01 11:00:00", UTILITARIO))
				.toString());
	}

	private static Animal createAnimal(String name, String dob, SpeciesEnum species) {
		return Animal.builder().name(name).dob(stringToDate(dob, ISO8601_COMPLETE_DATE_HOUR))
				.species(Species.builder().code(species).build()).build();
	}

	private static Carro createCarro(String codigo, String descricao, String dataCriacao, String dataAtualizacao,
			TipoCarroEnum tipoCarro) {
		return Carro.builder().codigo(codigo).descricao(descricao)
				.dataCriacao(stringToDate(dataCriacao, ISO8601_COMPLETE_DATE_HOUR))
				.dataAtualizacao(stringToDate(dataAtualizacao, ISO8601_COMPLETE_DATE_HOUR))
				.tipoCarro(TipoCarro.builder().code(tipoCarro).build()).build();
	}

}