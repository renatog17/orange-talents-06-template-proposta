package br.com.zup.renato.proposta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="propostas", url="http://localhost:9999/api/solicitacao")
public interface VerificaStatusClient {

	@RequestMapping(method = RequestMethod.POST, value="")
	VerificaStatusRequest verifica(VerificaStatusSend verificaStatusSend);
}
