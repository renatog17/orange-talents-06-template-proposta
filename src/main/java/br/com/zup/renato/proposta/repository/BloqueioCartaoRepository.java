package br.com.zup.renato.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.BloqueioCartao;
import br.com.zup.renato.proposta.model.Cartao;

public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {
	
	Optional<BloqueioCartao> findByCartao(Cartao cartao);
}
