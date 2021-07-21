package br.com.zup.renato.proposta.controller.form;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import br.com.zup.renato.proposta.model.Biometria;
import br.com.zup.renato.proposta.model.Cartao;

public class BiometriaForm {

	@NotBlank 
	private String fingerprint;

	public String getFingerprint() {
		return fingerprint;
	}

	public Biometria toModel(Cartao cartao) {
		String biometriaDecode = new String(Base64.getDecoder().decode(this.fingerprint));
		Biometria biometria = new Biometria(cartao, biometriaDecode);
		return biometria;
	}
}
