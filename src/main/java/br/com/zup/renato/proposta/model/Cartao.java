package br.com.zup.renato.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.renato.proposta.model.enums.StatusCartao;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String idCartao;
	@OneToOne
	@NotNull
	private Proposta proposta;
	private StatusCartao statusCartao;

	@Deprecated
	public Cartao() {
		super();
	}

	public Cartao(@NotBlank String idCartao, @NotNull Proposta proposta) {
		super();
		this.idCartao = idCartao;
		this.proposta = proposta;
		statusCartao = StatusCartao.NAO_BLOQUEADO;
	}

	public String getIdCartao() {
		return idCartao;
	}

	public void bloquearCartao() {
		this.statusCartao = StatusCartao.BLOQUEADO;
	}
	
	

}
