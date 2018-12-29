package br.com.grupomult.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.grupomult.api.animal.models.Animal.SpeciesEnum;
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
public class Species implements Serializable {

	private static final long serialVersionUID = 5217289358964051431L;

	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private SpeciesEnum code;

}
