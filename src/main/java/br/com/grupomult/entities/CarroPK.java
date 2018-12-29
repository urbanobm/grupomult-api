package br.com.grupomult.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@Embeddable
public class CarroPK implements Serializable {

	private static final long serialVersionUID = -6909015604206875054L;

	private Long id;
	
	@ManyToOne
	private TipoCarro tipoCarro;
	
	public CarroPK(){
		this.id = generateId(); 
	}

	public CarroPK(TipoCarro tipoCarro) {
		this.id = generateId() ; 
		this.tipoCarro = tipoCarro; 
	}
	
	public CarroPK(Long id, TipoCarro tipoCarro){
		this.id = id; 
		this.tipoCarro = tipoCarro; 
	}

	private Long generateId() {
		return new Date().getTime();
	}
	
}
