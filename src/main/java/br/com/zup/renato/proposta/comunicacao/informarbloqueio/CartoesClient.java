package br.com.zup.renato.proposta.comunicacao.informarbloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.renato.proposta.comunicacao.informarbloqueio.bloqueio.ResultadoBloqueioResponse;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.bloqueio.ResultadoBloqueioSend;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.dadoscartao.DadosCartaoResponse;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.dadoscartao.DadosCartaoSend;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.viagem.InformarViagemResponse;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.viagem.InformarViagemSend;

@FeignClient(name="resultadobloqueio", url="http://localhost:8888/api/cartoes")
public interface CartoesClient {

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value="/{id}/bloqueios")
	ResultadoBloqueioResponse informarBloqueio(@PathVariable("id") String idCartao, ResultadoBloqueioSend resultadoBloqueioSend);

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value="/{id}/avisos")
	InformarViagemResponse informarViagem(@PathVariable("id") String idCartao, InformarViagemSend resultadoInformarViagemSend);
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value="")
	DadosCartaoResponse verifica(DadosCartaoSend dadosCartaoSend);
}
