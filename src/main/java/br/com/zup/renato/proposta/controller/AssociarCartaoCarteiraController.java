package br.com.zup.renato.proposta.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.renato.proposta.comunicacao.clientecartao.CartoesClient;
import br.com.zup.renato.proposta.comunicacao.clientecartao.carteira.CarteiraResponse;
import br.com.zup.renato.proposta.comunicacao.clientecartao.carteira.CarteiraSend;
import br.com.zup.renato.proposta.controller.form.AssociarCarteiraForm;
import br.com.zup.renato.proposta.controller.validacao.erropadronizado.ApiErroException;
import br.com.zup.renato.proposta.model.Cartao;
import br.com.zup.renato.proposta.model.Carteira;
import br.com.zup.renato.proposta.repository.CartaoRepository;
import br.com.zup.renato.proposta.repository.CarteiraRepository;

@RequestMapping("/associar-cartao")
@RestController
public class AssociarCartaoCarteiraController {

	@Autowired
	private CartoesClient cartoesClient;
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private CarteiraRepository carteiraRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> associarCartao(@PathVariable String id, UriComponentsBuilder uriComponentsBuilder,
			@RequestBody @Valid AssociarCarteiraForm associarCarteiraForm) {
		Optional<Cartao> cartao = cartaoRepository.findByIdCartao(id);
		if (cartao.isEmpty()) {
			throw new ApiErroException(HttpStatus.NOT_FOUND, "cartão não encontrado");
		}

		CarteiraSend carteiraSend = new CarteiraSend(cartao.get().getProposta().getEmail(),
				associarCarteiraForm.getCarteira());

		try {
			CarteiraResponse carteiraResponse = cartoesClient.associarCartao(cartao.get().getIdCartao(), carteiraSend);
			Carteira carteira = new Carteira(carteiraResponse.getId(), associarCarteiraForm.getCarteira(),
					cartao.get());
			carteiraRepository.save(carteira);
			return ResponseEntity.created(
					uriComponentsBuilder.path("/carteira/{id}").buildAndExpand(carteira.getIdCarteira()).toUri())
					.build();
		} catch (Exception e) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse cartão já foi associado a uma carteira");
		}
	}
}
