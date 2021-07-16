package br.com.zup.renato.proposta.controller.validacao;

public class ErroValidacao {

	private String campo;
	private String erro;

	public ErroValidacao(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
}
