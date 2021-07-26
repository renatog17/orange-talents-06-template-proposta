package br.com.zup.renato.proposta.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.renato.proposta.controller.validacao.CarteiraValida;


public class AssociarCarteiraForm {

	@CarteiraValida
	@NotBlank
	private String carteira;

	public String getCarteira() {
		return carteira;
	}

}
