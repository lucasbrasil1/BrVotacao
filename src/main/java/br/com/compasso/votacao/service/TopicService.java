package br.com.compasso.votacao.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.repository.TopicRepository;

@Service
public class TopicService {

	private SessionService sessionService;
	private TopicRepository topicRepository;
	
	public TopicService(TopicRepository topicRepository, SessionService sessionService) {
		this.topicRepository = topicRepository;
		this.sessionService = sessionService;
	}

	public Optional<Topic> getOne(Long id) {
		return topicRepository.findById(id);
	}

	@Transactional
	public Topic initialize(Topic topic, int minutes) {
		sessionService.start(topic, minutes);
		return topicRepository.save(topic);
	}

	public Page<Topic> findAll(Pageable paginacao) {
		return topicRepository.findAll(paginacao);
	}

	public Page<Topic> findByTitleContains(String topicTitle, Pageable paginacao) {
		return topicRepository.findByTitleContains(topicTitle, paginacao);
	}
}
