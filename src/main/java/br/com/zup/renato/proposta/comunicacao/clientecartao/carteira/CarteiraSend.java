package br.com.zup.renato.proposta.comunicacao.clientecartao.carteira;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraSend {

	@NotBlank
	private String email;
	@NotBlank
	private String carteira;

	public CarteiraSend(@NotBlank String email, @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

}
