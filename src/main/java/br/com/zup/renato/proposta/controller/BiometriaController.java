package br.com.zup.renato.proposta.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.renato.proposta.controller.form.BiometriaForm;
import br.com.zup.renato.proposta.model.Biometria;
import br.com.zup.renato.proposta.model.Cartao;
import br.com.zup.renato.proposta.repository.BiometriaRepository;
import br.com.zup.renato.proposta.repository.CartaoRepository;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@PostMapping("/{idCartao}")
	public ResponseEntity<?> cadastrarBiometria(@PathVariable String idCartao, 
			@RequestBody @Valid BiometriaForm biometriaForm, UriComponentsBuilder uriComponentsBuilder){
		Optional<Cartao> cartao = cartaoRepository.findByIdCartao(idCartao);
		System.out.println(cartao.get().getIdCartao());
		if(cartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Biometria biometria = biometriaForm.toModel(cartao.get());
		biometriaRepository.save(biometria);
		return ResponseEntity
				.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(biometria.getId()).toUri())
				.build();
	}
}
