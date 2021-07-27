package br.com.zup.renato.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.renato.proposta.model.enums.StatusRestricao;
import br.com.zup.renato.proposta.model.util.CpfOuCnpjLimpo;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String cpfOuCnpj;
	private @NotBlank @Email String email;
	private @NotBlank String endereco;
	private @NotBlank String nome;
	private @NotNull @Positive Double salario;
	private StatusRestricao statusRestricao;

	@Deprecated
	public Proposta() {
		super();
	}

	public Proposta(@NotBlank CpfOuCnpjLimpo cpfOuCnpjLimpo, @NotBlank @Email String email, @NotBlank String endereco,
			@NotBlank String nome, @NotNull @Positive Double salario) {
		this.cpfOuCnpj = cpfOuCnpjLimpo.hash();
		this.email = email;
		this.endereco = endereco;
		this.nome = nome;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public String getNome() {
		return nome;
	}

	public StatusRestricao getStatusRestricao() {
		return statusRestricao;
	}

	public void setStatusRestricao(StatusRestricao statusRestricao) {
		this.statusRestricao = statusRestricao;
	}

	public String getEmail() {
		return email;
	}

}
