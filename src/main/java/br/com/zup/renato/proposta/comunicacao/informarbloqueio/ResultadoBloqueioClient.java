package br.com.zup.renato.proposta.comunicacao.informarbloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="resultadobloqueio", url="http://localhost:8888/api/cartoes")
public interface ResultadoBloqueioClient {

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value="/{id}/bloqueios")
	ResultadoBloqueioRequest informarBloqueio(@PathVariable("id") String idCartao, ResultadoBloqueioSend resultadoBloqueioSend);
}
