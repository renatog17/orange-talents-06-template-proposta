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

import br.com.zup.renato.proposta.controller.form.PropostaForm;
import br.com.zup.renato.proposta.model.Proposta;
import br.com.zup.renato.proposta.repository.PropostaRepository;

@RestController
@RequestMapping(value = "/propostas")
public class PropostaController {

	@Autowired
	PropostaRepository propostaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaForm propostaForm, UriComponentsBuilder uriComponentsBuilder){
		
		Optional<Proposta> p = propostaRepository.findByCpfOuCnpj(propostaForm.getCpfOuCnpj());
		
		if(p.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadastrado");
		}
		Proposta proposta = propostaForm.toModel();
		
		propostaRepository.save(proposta);
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}")
				.buildAndExpand(proposta.getId()).toUri()).body(proposta);
	}
}
