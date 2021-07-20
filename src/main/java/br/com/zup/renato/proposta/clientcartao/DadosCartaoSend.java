package br.com.zup.renato.proposta.clientcartao;

public class DadosCartaoSend {
	private String documento;
	private String nome;
	private String idProposta;

	public DadosCartaoSend(String documento, String nome, String idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdSolicitacao() {
		return idProposta;
	}

}
