package br.com.zup.renato.proposta.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
		Proposta proposta = propostaForm.toModel();
		propostaRepository.save(proposta);
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}")
				.buildAndExpand(proposta.getId()).toUri()).body(proposta);
	}
}
