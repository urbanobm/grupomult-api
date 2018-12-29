package br.com.grupomult.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.grupomult.api.carro.models.Carro.TipoCarroEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TipoCarro implements Serializable {

	private static final long serialVersionUID = -3644009450956415689L;

	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private TipoCarroEnum code;

}
