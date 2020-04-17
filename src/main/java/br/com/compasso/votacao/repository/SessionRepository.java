package br.com.compasso.votacao.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

	public Optional<Session> findByTopic_Id(Long id);
	
	public List<Session> findByEndingLessThan(LocalDateTime now);

	public Page<Session> findByTopicTitleContains(String topicTitle, Pageable pageable);


}
