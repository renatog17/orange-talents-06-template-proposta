package br.com.zup.renato.proposta.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.renato.proposta.comunicacao.informarbloqueio.ResultadoBloqueioClient;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.ResultadoBloqueioRequest;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.ResultadoBloqueioSend;
import br.com.zup.renato.proposta.controller.validacao.erropadronizado.ApiErroException;
import br.com.zup.renato.proposta.model.BloqueioCartao;
import br.com.zup.renato.proposta.model.Cartao;
import br.com.zup.renato.proposta.repository.BloqueioCartaoRepository;
import br.com.zup.renato.proposta.repository.CartaoRepository;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioCartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private BloqueioCartaoRepository bloqueioCartaoRepository;
	@Autowired
	private ResultadoBloqueioClient resultadoBloqueioClient;

	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> bloquarCartao(@PathVariable String idCartao, HttpServletRequest request) {
		Optional<Cartao> cartao = cartaoRepository.findByIdCartao(idCartao);
		if (cartao.isEmpty()) {
			throw new ApiErroException(HttpStatus.BAD_REQUEST, "Cartão não encontrado");
		}
		Optional<BloqueioCartao> bloqueioCartao = bloqueioCartaoRepository.findByCartao(cartao.get());
		if(bloqueioCartao.isPresent()) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão já foi bloqueado");
		}
		ResultadoBloqueioSend send = new ResultadoBloqueioSend("proposta");
		ResultadoBloqueioRequest informarBloqueio;
		try {	
			informarBloqueio = resultadoBloqueioClient.informarBloqueio(idCartao, send);
			BloqueioCartao bloqueioCartao2 = new BloqueioCartao(request.getRemoteAddr(), request.getRemoteUser(), cartao.get());
			bloqueioCartaoRepository.save(bloqueioCartao2);
			cartao.get().bloquearCartao();
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível bloquear esse cartão em um servidor externo");
		}
	}
}
