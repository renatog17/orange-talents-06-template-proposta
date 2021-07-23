package br.com.zup.renato.proposta.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.renato.proposta.controller.form.AvisoViagemForm;
import br.com.zup.renato.proposta.controller.validacao.erropadronizado.ApiErroException;
import br.com.zup.renato.proposta.model.AvisoViagem;
import br.com.zup.renato.proposta.model.Cartao;
import br.com.zup.renato.proposta.repository.AvisoViagemRepository;
import br.com.zup.renato.proposta.repository.CartaoRepository;

@RestController
@RequestMapping("/aviso-viagens")
public class ViagemController {

	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private AvisoViagemRepository avisoViagemRepository;
	
	@PostMapping("/{idCartao}")
	public ResponseEntity<?> avisarViagem(@PathVariable String idCartao ,@Valid @RequestBody AvisoViagemForm avisoViagemForm,
			HttpServletRequest request){
		Optional<Cartao> cartao = cartaoRepository.findByIdCartao(idCartao);
		if(cartao.isEmpty()) {
			throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrado um cartão para este id");
		}
		AvisoViagem avisoViagem = avisoViagemForm.toModel(cartao.get(), request);
		avisoViagemRepository.save(avisoViagem);
		return ResponseEntity.ok().build();
	}
}
