package br.com.compasso.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
