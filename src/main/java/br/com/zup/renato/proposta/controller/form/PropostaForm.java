package br.com.zup.renato.proposta.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.renato.proposta.controller.validacao.CPFOrCNPJ;
import br.com.zup.renato.proposta.controller.validacao.UniqueValue;
import br.com.zup.renato.proposta.model.Proposta;

public class PropostaForm {

	@CPFOrCNPJ
	@NotBlank 
	private String cpfOuCnpj;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull @Positive
	private Double salario;

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public Double getSalario() {
		return salario;
	}
	
	public Proposta toModel() {
		return new Proposta(this.cpfOuCnpj, this.email, this.endereco, this.nome, this.salario);
	}

}
