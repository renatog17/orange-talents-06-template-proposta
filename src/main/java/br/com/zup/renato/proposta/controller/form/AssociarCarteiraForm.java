package br.com.zup.renato.proposta.controller.form;

import javax.validation.constraints.NotBlank;

public class AssociarCarteiraForm {

	@NotBlank
	private String carteira;

	public String getCarteira() {
		return carteira;
	}

}
