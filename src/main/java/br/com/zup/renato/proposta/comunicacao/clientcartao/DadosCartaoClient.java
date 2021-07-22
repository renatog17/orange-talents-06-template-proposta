package br.com.zup.renato.proposta.comunicacao.clientcartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="cartoes", url="http://localhost:8888/api/cartoes")
public interface DadosCartaoClient {

	@RequestMapping(method = RequestMethod.POST, value="")
	DadosCartaoRequest verifica(DadosCartaoSend dadosCartaoSend);
}
