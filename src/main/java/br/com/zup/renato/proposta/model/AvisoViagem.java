package br.com.zup.renato.proposta.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Cartao cartao;
	@NotBlank
	private String destinoViagem;
	@NotNull
	private LocalDate dataTermino;
	@NotNull
	private LocalDateTime instanteNotificacao = LocalDateTime.now();
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String userAgente;

	public AvisoViagem(@NotNull Cartao cartao, @NotBlank String destinoViagem, @NotNull LocalDate dataTermino,
			@NotBlank String ipCliente, @NotBlank String userAgente) {
		super();
		this.cartao = cartao;
		this.destinoViagem = destinoViagem;
		this.dataTermino = dataTermino;
		this.ipCliente = ipCliente;
		this.userAgente = userAgente;
	}
}
