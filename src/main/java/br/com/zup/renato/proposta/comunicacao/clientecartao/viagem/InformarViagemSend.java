package br.com.zup.renato.proposta.comunicacao.clientecartao.viagem;

import java.time.LocalDate;

public class InformarViagemSend {

	private String destino;
	private String validoAte;

	public InformarViagemSend(String destino, String validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public String getValidoAte() {
		return validoAte;
	}

	
}
