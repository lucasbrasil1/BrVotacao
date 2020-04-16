package br.com.compasso.votacao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	Page<Topic> findByTitleContains(String topicTitle, Pageable paginacao);

}
