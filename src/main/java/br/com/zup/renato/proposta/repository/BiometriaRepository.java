package br.com.zup.renato.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.renato.proposta.model.Biometria;

public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
	
}
