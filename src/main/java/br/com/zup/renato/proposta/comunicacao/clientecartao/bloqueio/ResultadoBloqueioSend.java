package br.com.zup.renato.proposta.comunicacao.clientecartao.bloqueio;

public class ResultadoBloqueioSend {

	private String sistemaResponsavel;

	public ResultadoBloqueioSend(String sistemaResponsavel) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
