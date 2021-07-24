package br.com.zup.renato.proposta.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.renato.proposta.comunicacao.clientproposta.VerificaStatusClient;
import br.com.zup.renato.proposta.comunicacao.clientproposta.VerificaStatusRequest;
import br.com.zup.renato.proposta.comunicacao.clientproposta.VerificaStatusSend;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.CartoesClient;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.dadoscartao.DadosCartaoResponse;
import br.com.zup.renato.proposta.comunicacao.informarbloqueio.dadoscartao.DadosCartaoSend;
import br.com.zup.renato.proposta.controller.dto.PropostaDto;
import br.com.zup.renato.proposta.controller.form.PropostaForm;
import br.com.zup.renato.proposta.metricas.MinhasMetricas;
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
	private CartoesClient cartoesClient;
	@Autowired
	private MinhasMetricas minhasMetricas;
	
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
		proposta.setStatusRestricao(statusRestricao);

		propostaRepository.save(proposta);
		
		criaCartao(proposta);
		minhasMetricas.meuContador();
		return ResponseEntity
				.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri())
				.body(proposta);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaDto> consultar(@PathVariable Long id){
		Optional<Proposta> proposta = propostaRepository.findById(id);
		if(proposta.isPresent()) {
			System.out.println(proposta.get().getStatusRestricao()+"!!!");
			PropostaDto propostaDto = new PropostaDto(proposta.get());
			return ResponseEntity.ok(propostaDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	private StatusRestricao consultaExterna(Proposta proposta) {
		VerificaStatusSend verificaStatusSend = new VerificaStatusSend(proposta.getCpfOuCnpj(), proposta.getNome(),
				proposta.getId().toString());
		VerificaStatusRequest verifica = null;
		try {
			verifica = verificaStatusClient.verifica(verificaStatusSend);
		} catch (Exception e) {
			proposta.setStatusRestricao(StatusRestricao.COM_RESTRICAO);
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
		DadosCartaoResponse verifica = cartoesClient.verifica(dadosCartaoSend);
		System.out.println(verifica.getId());
		Cartao cartao = new Cartao(verifica.getId(), proposta);
		cartaoRepository.save(cartao);
	}
}
