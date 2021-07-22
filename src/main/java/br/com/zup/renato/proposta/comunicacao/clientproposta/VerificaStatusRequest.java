package br.com.zup.renato.proposta.comunicacao.clientproposta;

public class VerificaStatusRequest {

	private String documento;
	private String nome;
	private String resultadoSolicitacao;
	private String idProposta;
	
	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdSolicitacao() {
		return idProposta;
	}

	@Override
	public String toString() {
		return "PropostaRequest [documento=" + documento + ", nome=" + nome + ", resultadoSolicitacao="
				+ resultadoSolicitacao + ", idProposta=" + idProposta + "]";
	}

}
