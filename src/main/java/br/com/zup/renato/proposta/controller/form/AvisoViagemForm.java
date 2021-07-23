package br.com.zup.renato.proposta.controller.form;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.renato.proposta.model.AvisoViagem;
import br.com.zup.renato.proposta.model.Cartao;

public class AvisoViagemForm {

	@NotBlank
	private String destinoViagem;
	@NotNull
	@Future
	private LocalDate terminoViagem;

	public String getDestinoViagem() {
		return destinoViagem;
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public AvisoViagem toModel(Cartao cartao, HttpServletRequest request) {
		AvisoViagem avisoViagem = new AvisoViagem(cartao, this.destinoViagem, terminoViagem,
				request.getRemoteAddr(), request.getRemoteUser());
		return avisoViagem;
	}
}
