package br.com.compasso.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Associate;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
	
	Optional<Associate> findByEmail(String email);
}
