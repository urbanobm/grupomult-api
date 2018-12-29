package br.com.grupomult.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Animal implements Serializable {

	private static final long serialVersionUID = 4944015990569194437L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	@ManyToOne(cascade = CascadeType.ALL)
	private Species species;

}
