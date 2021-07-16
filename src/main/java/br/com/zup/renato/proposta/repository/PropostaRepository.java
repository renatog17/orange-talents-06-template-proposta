package br.com.zup.renato.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByCpfOuCnpj(String cpfOuCnpj);
}
