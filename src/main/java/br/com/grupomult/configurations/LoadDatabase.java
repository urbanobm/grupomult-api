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
import br.com.grupomult.entities.Animal;
import br.com.grupomult.entities.Carro;
import br.com.grupomult.entities.CarroPK;
import br.com.grupomult.entities.Species;
import br.com.grupomult.entities.TipoCarro;
import br.com.grupomult.repositories.AnimalRepository;
import br.com.grupomult.repositories.CarroRepository;
import br.com.grupomult.repositories.TipoCarroRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase implements CommandLineRunner {

	private static final String PRELOADING = "Preloading ";

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
	private TipoCarroRepository tipoCarroRepository;

	@Override
	public void run(String... args) throws Exception {
		initDatabaseAnimal();
		initDatabaseCarro();
	}

	public void initDatabaseAnimal() {
		log.info(
				PRELOADING + animalRepository.save(createAnimal("Janis", "2015-02-09 11:00:00", CANINE)).toString());
		log.info(
				PRELOADING + animalRepository.save(createAnimal("Rocky", "2014-10-15 11:00:00", FELINE)).toString());
	}

	public void initDatabaseCarro() {
		TipoCarro tipoCarroPasseio = tipoCarroRepository.save(TipoCarro.builder().code(PASSEIO).build());
		TipoCarro tipoCarroUtilitario = tipoCarroRepository.save(TipoCarro.builder().code(UTILITARIO).build());
		
		Carro carroPasseio = createCarro("PLR1235", "Hiunday i30", "2017-01-01 11:00:00", "2017-02-01 11:00:00", tipoCarroPasseio);
		carroPasseio = carroRepository.save(carroPasseio);
		log.info(PRELOADING + carroPasseio.toString());
		
		Carro carroUtilitario = createCarro("PLR1235", "Hiunday ix35", "2018-01-01 11:00:00", "2018-02-01 11:00:00", tipoCarroUtilitario);
		carroUtilitario = carroRepository.save(carroUtilitario);
		log.info(PRELOADING + carroUtilitario.toString());
	}

	private static Animal createAnimal(String name, String dob, SpeciesEnum species) {
		return Animal.builder().name(name).dob(stringToDate(dob, ISO8601_COMPLETE_DATE_HOUR))
				.species(Species.builder().code(species).build()).build();
	}

	private static Carro createCarro(String codigo, String descricao, String dataCriacao, String dataAtualizacao,
			TipoCarro tipoCarro) {
		return Carro.builder().codigo(codigo).descricao(descricao)
				.dataCriacao(stringToDate(dataCriacao, ISO8601_COMPLETE_DATE_HOUR))
				.dataAtualizacao(stringToDate(dataAtualizacao, ISO8601_COMPLETE_DATE_HOUR))
				.tipoCarro(tipoCarro)
				.carroPK(new CarroPK(tipoCarro)).build();
	}

}