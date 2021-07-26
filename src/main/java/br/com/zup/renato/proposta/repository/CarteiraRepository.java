package br.com.zup.renato.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.Cartao;
import br.com.zup.renato.proposta.model.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
