package br.com.compasso.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

	public Optional<Session> findByTopic_Id(Long id);

}
