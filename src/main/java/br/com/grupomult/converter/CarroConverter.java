package br.com.grupomult.converter;

import static br.com.grupomult.utils.DateUtils.ISO8601_COMPLETE_DATE_HOUR;
import static br.com.grupomult.utils.DateUtils.dateToString;
import static br.com.grupomult.utils.DateUtils.stringToDate;

import br.com.grupomult.api.carro.models.Carro;
import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import br.com.grupomult.entities.CarroPK;
import br.com.grupomult.entities.TipoCarro;

public abstract class CarroConverter {
	
	protected static final Carro convertEntityToDomain(br.com.grupomult.entities.Carro entity) {
		if (entity == null) {
			return null;
		}

		Carro domain = new Carro();
		domain.setId(entity.getId());
		domain.setCodigo(entity.getCodigo());
		domain.setDescricao(entity.getDescricao());
		domain.setDataCriacao(dateToString(entity.getDataCriacao(), ISO8601_COMPLETE_DATE_HOUR));
		domain.setDataAtualizacao(dateToString(entity.getDataAtualizacao(), ISO8601_COMPLETE_DATE_HOUR));
		domain.setTipoCarro(entity.getTipoCarro().getCode());

		return domain;
	}

	protected static final br.com.grupomult.entities.Carro convertDomainToEntity(Carro domain) {
		if (domain == null || domain.getTipoCarro() == null) {
			return null;
		}

		br.com.grupomult.entities.Carro entity = new br.com.grupomult.entities.Carro();
		entity.setId(domain.getId());
		entity.setCodigo(domain.getCodigo());
		entity.setDescricao(domain.getDescricao());
		entity.setDataCriacao(stringToDate(domain.getDataCriacao(), ISO8601_COMPLETE_DATE_HOUR));
		entity.setDataAtualizacao(stringToDate(domain.getDataAtualizacao(), ISO8601_COMPLETE_DATE_HOUR));
		entity.setTipoCarro(criarTipoCarro(domain.getTipoCarro()));
		entity.setCarroPK(new CarroPK(domain.getId(), criarTipoCarro(domain.getTipoCarro())));

		return entity;
	}

	private static TipoCarro criarTipoCarro(TipoCarroEnum tipoCarroEnum) {
		if (tipoCarroEnum == null) {
			return null;
		}
		return TipoCarro.builder().code(tipoCarroEnum).build();
	}

}
