package br.com.zup.renato.proposta.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.renato.proposta.client.VerificaStatusClient;
import br.com.zup.renato.proposta.client.VerificaStatusRequest;
import br.com.zup.renato.proposta.client.VerificaStatusSend;
import br.com.zup.renato.proposta.clientcartao.DadosCartaoClient;
import br.com.zup.renato.proposta.clientcartao.DadosCartaoRequest;
import br.com.zup.renato.proposta.clientcartao.DadosCartaoSend;
import br.com.zup.renato.proposta.controller.form.PropostaForm;
import br.com.zup.renato.proposta.model.Cartao;
import br.com.zup.renato.proposta.model.Proposta;
import br.com.zup.renato.proposta.model.enums.StatusRestricao;
import br.com.zup.renato.proposta.repository.CartaoRepository;
import br.com.zup.renato.proposta.repository.PropostaRepository;

@RestController
@RequestMapping(value = "/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private VerificaStatusClient verificaStatusClient;
	@Autowired
	private DadosCartaoClient dadosCartaoClient;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaForm propostaForm,
			UriComponentsBuilder uriComponentsBuilder) {

		Optional<Proposta> p = propostaRepository.findByCpfOuCnpj(propostaForm.getCpfOuCnpj());

		if (p.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadastrado");
		}
		Proposta proposta = propostaForm.toModel();

		propostaRepository.save(proposta);

		StatusRestricao statusRestricao = consultaExterna(proposta);
		proposta.setIsElegivel(statusRestricao);

		propostaRepository.save(proposta);
		
		criaCartao(proposta);
		return ResponseEntity
				.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri())
				.body(proposta);
	}

	private StatusRestricao consultaExterna(Proposta proposta) {
		VerificaStatusSend verificaStatusSend = new VerificaStatusSend(proposta.getCpfOuCnpj(), proposta.getNome(),
				proposta.getId().toString());
		VerificaStatusRequest verifica = null;
		try {
			verifica = verificaStatusClient.verifica(verificaStatusSend);
		} catch (Exception e) {
			proposta.setIsElegivel(StatusRestricao.COM_RESTRICAO);
			return StatusRestricao.COM_RESTRICAO;
		}
		if (verifica != null && verifica.getResultadoSolicitacao().equals("SEM_RESTRICAO")) {
			return StatusRestricao.SEM_RESTRICAO;
		}
		return null;
	}
	
	private void criaCartao(Proposta proposta) {
		DadosCartaoSend dadosCartaoSend = new DadosCartaoSend(proposta.getCpfOuCnpj(), proposta.getNome(),
				proposta.getId().toString());
		DadosCartaoRequest verifica = dadosCartaoClient.verifica(dadosCartaoSend);
		System.out.println(verifica.getId());
		Cartao cartao = new Cartao(verifica.getId(), proposta);
		cartaoRepository.save(cartao);
	}
}
