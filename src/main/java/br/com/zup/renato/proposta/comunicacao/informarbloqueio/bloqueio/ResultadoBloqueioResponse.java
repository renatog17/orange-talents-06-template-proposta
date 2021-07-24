package br.com.zup.renato.proposta.comunicacao.informarbloqueio.bloqueio;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ResultadoBloqueioResponse implements ErrorDecoder{

	private String resultado;

	public String getResultado() {
		return resultado;
	}

	@Override
	public Exception decode(String methodKey, Response response) {
		System.out.println(response.status());
		return null;
	}
	
	
}
