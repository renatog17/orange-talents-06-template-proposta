package br.com.zup.renato.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.AvisoViagem;

public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
	
}
