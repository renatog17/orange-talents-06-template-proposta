package br.com.zup.renato.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
