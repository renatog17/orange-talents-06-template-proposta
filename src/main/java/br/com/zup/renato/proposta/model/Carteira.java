package br.com.zup.renato.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String idCarteira;
	@NotBlank
	private String tipoCarteira;
	@NotNull
	@ManyToOne
	private Cartao cartao;

	public Carteira(@NotBlank String idCarteira, @NotBlank String tipoCarteira, @NotNull Cartao cartao) {
		super();
		this.idCarteira = idCarteira;
		this.tipoCarteira = tipoCarteira;
		this.cartao = cartao;
	}

	public String getIdCarteira() {
		return idCarteira;
	}

	
	
}
