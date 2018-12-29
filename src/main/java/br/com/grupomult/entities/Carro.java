package br.com.grupomult.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
public class Carro implements Serializable {

	private static final long serialVersionUID = -2756126323782207245L;

    @EmbeddedId
    private CarroPK carroPK;
    
    @Transient
	private Long id;
	
    @Transient
    private TipoCarro tipoCarro;
	
	private String codigo;
	
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;

	public Long getId(){
		if(this.getCarroPK() == null){
			return null;
		}
		return this.getCarroPK().getId();
	}

	public TipoCarro getTipoCarro(){
		if(this.getCarroPK() == null){
			return null;
		}
		return this.getCarroPK().getTipoCarro();
	}
	
}
