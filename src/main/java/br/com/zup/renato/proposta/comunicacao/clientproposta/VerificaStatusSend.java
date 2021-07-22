package br.com.zup.renato.proposta.comunicacao.clientproposta;

public class VerificaStatusSend {
	private String documento;
	private String nome;
	private String idProposta;

	public VerificaStatusSend(String documento, String nome, String idProposta) {
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
