package br.com.zup.renato.proposta.controller.validacao.erropadronizado;

import java.util.Collection;

public class ErroPadronizado {

	private Collection<String> mensagens;

	public ErroPadronizado(Collection<String> mensagens) {
		super();
		this.mensagens = mensagens;
	}

	public Collection<String> getMensagens() {
		return mensagens;
	}

}
