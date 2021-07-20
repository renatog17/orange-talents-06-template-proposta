package br.com.zup.renato.proposta.controller.dto;

import br.com.zup.renato.proposta.model.Proposta;
import br.com.zup.renato.proposta.model.enums.StatusRestricao;

public class PropostaDto {

	private StatusRestricao statusProposta;

	public PropostaDto(Proposta proposta) {
		super();
		this.statusProposta = proposta.getStatusRestricao();
	}

	public StatusRestricao getStatusProposta() {
		return statusProposta;
	}

}
