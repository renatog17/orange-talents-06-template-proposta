package br.com.zup.renato.proposta.model.util;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class CpfOuCnpjLimpo {

	private String cpfOuCnpj;

	public CpfOuCnpjLimpo(String cpfOuCnpj) {
		super();
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String hash() {
		TextEncryptor text = Encryptors.text("qualquercoisa", "5c0744940b5c369b");
		String c = text.encrypt(cpfOuCnpj);
		System.out.println(c);
		text = Encryptors.text("qualquercoiaa", "5c0744940b5c369b");
		System.out.println(text.decrypt(c));
		return null;
	}
}

