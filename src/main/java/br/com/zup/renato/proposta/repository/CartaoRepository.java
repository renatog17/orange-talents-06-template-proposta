package br.com.zup.renato.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	Optional<Cartao> findByIdCartao(String idCartao);
}
