package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired 
	private SessionService sessionService;

	public Optional<Topic> getOne(Long id) {
		return topicRepository.findById(id);
	}

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	public void save(Topic topic) {
		topicRepository.save(topic);
	}

	@Transactional
	public void initialize(Topic topic) {
		sessionService.start(topic);
		topicRepository.save(topic);
	}
}
