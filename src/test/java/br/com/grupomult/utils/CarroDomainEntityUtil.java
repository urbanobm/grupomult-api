package br.com.grupomult.utils;

import static br.com.grupomult.utils.DateUtils.ISO8601_COMPLETE_DATE_HOUR;
import static br.com.grupomult.utils.DateUtils.dateToString;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.api.carro.models.ResponseGetCarrosById;
import br.com.grupomult.entities.TipoCarro;

public class CarroDomainEntityUtil {
	
	public static Carro criarDomainCarro(TipoCarroEnum tipoCarroEnum) {
		return criarDomainCarro(tipoCarroEnum, new Date(), new Date());
	}

	public static br.com.grupomult.entities.Carro criarEntityCarro(TipoCarroEnum tipoCarroEnum) {
		return criarEntityCarro(tipoCarroEnum, new Date(), new Date());
	}

	public static Carro criarDomainCarro(TipoCarroEnum tipoCarroEnum, Date dataCriacao, Date dataAtualizacao) {
		Carro carro = new Carro();
		carro.setCodigo("PLR4532");
		carro.setDataAtualizacao(dateToString(dataAtualizacao, ISO8601_COMPLETE_DATE_HOUR));
		carro.setDataCriacao(dateToString(dataCriacao, ISO8601_COMPLETE_DATE_HOUR));
		carro.setDescricao("Hyunday i30");
		carro.setTipoCarro(tipoCarroEnum);
		return carro;
	}

	public static br.com.grupomult.entities.Carro criarEntityCarro(TipoCarroEnum tipoCarroEnum, Date dataCriacao, Date dataAtualizacao) {
		br.com.grupomult.entities.Carro carro = br.com.grupomult.entities.Carro.builder().codigo("PLR4532")
				.dataAtualizacao(dataAtualizacao)
				.dataCriacao(dataCriacao)
				.descricao("Hyunday i30")
				.tipoCarro( criarTipoCarro(tipoCarroEnum)).build();
		return carro;
	}
	
	public static br.com.grupomult.entities.Carro createEntityCarro(String codigo, String dataCriacao, String dataAtualizacao, TipoCarroEnum tipoCarro) {
		return br.com.grupomult.entities.Carro.builder()
				.codigo(codigo)
				.dataCriacao(DateUtils.stringToDate(dataCriacao, DateUtils.ISO8601_COMPLETE_DATE_HOUR))
				.dataAtualizacao(DateUtils.stringToDate(dataAtualizacao, DateUtils.ISO8601_COMPLETE_DATE_HOUR))
				.tipoCarro(TipoCarro.builder().code(tipoCarro).build()).build();
	}


	public static TipoCarro criarTipoCarro(TipoCarroEnum tipoCarroEnum) {
		TipoCarro tipoCarro = TipoCarro.builder().id(tipoCarroEnum.ordinal()+1).code(tipoCarroEnum).build();
		return tipoCarro;
	}	
	
	public static ResponseEntity<ResponseGetCarrosById> createResponseEntityResponseGetCarrosById(Carro domain) {
		ResponseGetCarrosById response = new ResponseGetCarrosById();
		response.setCarro(domain);
		return ResponseEntity.ok(response);
	}


}
