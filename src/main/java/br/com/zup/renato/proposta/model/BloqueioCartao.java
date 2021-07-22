package br.com.zup.renato.proposta.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class BloqueioCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime instanteBloqueio = LocalDateTime.now();
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String userAgente;
	@OneToOne
	private Cartao cartao;

	@Deprecated
	public BloqueioCartao() {
		super();
	}

	public BloqueioCartao(@NotBlank String ipCliente, @NotBlank String userAgente, @NotNull Cartao cartao) {
		super();
		this.ipCliente = ipCliente;
		this.userAgente = userAgente;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "BloqueioCartao [id=" + id + ", instanteBloqueio=" + instanteBloqueio + ", ipCliente=" + ipCliente
				+ ", userAgente=" + userAgente + ", cartao=" + cartao + "]";
	}

	public Cartao getCartao() {
		return cartao;
	}

}
