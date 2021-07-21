package br.com.zup.renato.proposta.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Cartao cartao;
	@NotBlank
	private String figerprint;
	private LocalDate dataAssociacao = LocalDate.now();

	public Biometria(@NotNull Cartao cartao, @NotBlank String figerprint) {
		super();
		this.cartao = cartao;
		this.figerprint = figerprint;
	}

	public Long getId() {
		return id;
	}

	
}
